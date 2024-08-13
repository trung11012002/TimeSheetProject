package com.timesheet.timesheetproject.controller.auth;

import com.nimbusds.jose.JOSEException;
import com.timesheet.timesheetproject.dto.request.auth.AuthenticationResquest;
import com.timesheet.timesheetproject.dto.request.auth.IntrospectRequest;
import com.timesheet.timesheetproject.dto.request.auth.LogoutRequest;
import com.timesheet.timesheetproject.dto.request.auth.RefreshRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.auth.AuthenticationResponse;
import com.timesheet.timesheetproject.dto.response.auth.IntrospectResponse;
import com.timesheet.timesheetproject.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    IAuthenticationService authenticationService;
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationResquest resquest){
        var result =  authenticationService.authencticate(resquest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .code(1000)
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody @Valid IntrospectRequest resquest) throws ParseException, JOSEException {
        var result =  authenticationService.introspect(resquest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .code(1000)
                .build();
    }
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest resquest) throws ParseException, JOSEException {
        authenticationService.logout(resquest);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest resquest) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(resquest))
                .code(1000)
                .build();
    }
}
