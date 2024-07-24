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
import com.timesheet.timesheetproject.dto.response.PermissionResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.dto.response.UserProjection;
import com.timesheet.timesheetproject.dto.response.auth.AuthenticationResponse;
import com.timesheet.timesheetproject.dto.response.auth.IntrospectResponse;
import com.timesheet.timesheetproject.entity.InvalidatedToken;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.repository.InvalidatedTokenRepository;
import com.timesheet.timesheetproject.repository.UserRepository;
import com.timesheet.timesheetproject.service.IAuthenticationService;
import com.timesheet.timesheetproject.service.IPermissionService;
import com.timesheet.timesheetproject.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IRoleService roleService;

    @NonFinal
    @Value("${jwtSignerKey}")
    protected String SIGNER_KEY;
    @NonFinal
//    @Value("${valid-duration}")
//    protected  long VALID_DURATION;
    protected long VALID_DURATION = 6000;
    @NonFinal
//    @Value("${refreshable-duration}")
    protected long REFRESHABLE_DURATION = 1000;

    @Override
    public AuthenticationResponse authencticate(AuthenticationResquest resquest) {
        var user = userRepository.findProjectionByUsername(resquest.getUsername()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(resquest.getPassword(), user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);
        //role
        List<RoleResponse> roles = roleService.getRoleByUserId(user.getId());
        String listRole = "";
        for (RoleResponse role : roles) {
            listRole += (role.getCode() + " ");
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .role(listRole.trim())
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            LocalDate currentDate = LocalDate.now();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .date(currentDate)
                    .build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest refreshRequest) throws ParseException, JOSEException {

        SignedJWT signedJWT = verifyToken(refreshRequest.getToken(), true);
        var jti = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        LocalDate currentDate = LocalDate.now();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jti)
                .expiryTime(expiryTime)
                .date(currentDate)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findProjectionByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

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
                .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        if (!verified) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (expiryTime.before(new Date())) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String generateToken(UserProjection user) {
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

    private String converterRoleAndPermission(UserProjection user) {
        StringBuilder result = new StringBuilder("");
        //role
        List<RoleResponse> roles = roleService.getRoleByUserId(user.getId());
        for (RoleResponse role : roles) {
            result.append("ROLE_");
            result.append(role.getCode());
            result.append(" ");
        }
        //permission
        for (RoleResponse role : roles) {
            List<PermissionResponse> permissions = permissionService.getPermissionByRoleId(role.getId());
            for (PermissionResponse permisstion : permissions) {
                result.append(permisstion.getCode());
                result.append(" ");
            }
        }
        return result.toString().trim();
    }
}
