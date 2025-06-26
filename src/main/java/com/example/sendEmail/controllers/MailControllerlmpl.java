package com.example.sendEmail.controllers;

import com.example.sendEmail.models.MailModel;
import com.example.sendEmail.services.SendEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailControllerlmpl implements MailController {

    private SendEmailService sendEmailService;
    public MailControllerlmpl(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Override
    public ResponseEntity<String> sendEmail(@RequestBody MailModel mailModel){
        sendEmailService.sendSimpleMail(mailModel);
        return ResponseEntity.ok("Email sent");
    }

}
