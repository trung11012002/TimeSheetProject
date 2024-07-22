package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.email.Email;

public interface IEmailService {
    void sendEmail(Email email);
}
