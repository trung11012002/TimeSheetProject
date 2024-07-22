package com.timesheet.timesheetproject.service.impl;


import com.timesheet.timesheetproject.dto.request.email.Email;
import com.timesheet.timesheetproject.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailSevice implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @Override
    @Async
    public void sendEmail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom("vuquangtrung098765@gmail.com");
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody());
            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
