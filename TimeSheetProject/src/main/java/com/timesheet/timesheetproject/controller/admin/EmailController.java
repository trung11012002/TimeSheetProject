package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.request.email.Email;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private IEmailService emailService;
    @PostMapping("")
    public ApiResponse<String> sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("success")
                .build();
    }
}
