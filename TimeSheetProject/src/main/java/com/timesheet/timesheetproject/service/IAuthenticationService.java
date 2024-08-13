package com.timesheet.timesheetproject.service;

import com.nimbusds.jose.JOSEException;
import com.timesheet.timesheetproject.dto.request.auth.AuthenticationResquest;
import com.timesheet.timesheetproject.dto.request.auth.IntrospectRequest;
import com.timesheet.timesheetproject.dto.request.auth.LogoutRequest;
import com.timesheet.timesheetproject.dto.request.auth.RefreshRequest;
import com.timesheet.timesheetproject.dto.response.auth.AuthenticationResponse;
import com.timesheet.timesheetproject.dto.response.auth.IntrospectResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authencticate(AuthenticationResquest resquest);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest refreshRequest) throws ParseException, JOSEException;
}
