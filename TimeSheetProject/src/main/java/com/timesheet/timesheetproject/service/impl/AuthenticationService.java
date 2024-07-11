package com.timesheet.timesheetproject.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.timesheet.timesheetproject.dto.request.auth.AuthenticationResquest;
import com.timesheet.timesheetproject.dto.request.auth.IntrospectRequest;
import com.timesheet.timesheetproject.dto.request.auth.LogoutRequest;
import com.timesheet.timesheetproject.dto.request.auth.RefreshRequest;
import com.timesheet.timesheetproject.dto.response.auth.AuthenticationResponse;
import com.timesheet.timesheetproject.dto.response.auth.IntrospectResponse;
import com.timesheet.timesheetproject.entity.InvalidatedToken;
import com.timesheet.timesheetproject.entity.Permission;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.repository.InvalidatedTokenRepository;
import com.timesheet.timesheetproject.repository.UserRepository;
import com.timesheet.timesheetproject.service.IAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    @Value("${jwtSignerKey}")
    protected String SIGNER_KEY;
    @NonFinal
//    @Value("${valid-duration}")
//    protected  long VALID_DURATION;
    protected  long VALID_DURATION = 6000;
    @NonFinal
//    @Value("${refreshable-duration}")
    protected  long REFRESHABLE_DURATION = 1000;

    @Override
    public AuthenticationResponse authencticate(AuthenticationResquest resquest) {
        var user = userRepository.findByUsername(resquest.getUsername()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(resquest.getPassword(), user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .role(user.getRole().getCode())
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token,false);
        }catch (AppException e){
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(),true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception){
            log.info("Token already expired");
        }
    }

    @Override
    public Object refreshToken(RefreshRequest refreshRequest) throws ParseException, JOSEException {

        SignedJWT signedJWT = verifyToken(refreshRequest.getToken(),true);
        var jti = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jti).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                    .toInstant().plus(REFRESHABLE_DURATION,ChronoUnit.SECONDS)
                    .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        if(!verified) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (expiryTime.before(new Date())) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .claim("scope", converterRoleAndPermission(user))
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println("Cannot create token");
            throw new RuntimeException(e);
        }
    }
    private String converterRoleAndPermission(User user){
        StringBuilder result = new StringBuilder("");
        result.append("ROLE_" + user.getRole().getCode());
        for(Permission permisstion : user.getRole().getPermissions()){
            result.append(" " + permisstion.getCode());
        }
        return result.toString();
    }
}
