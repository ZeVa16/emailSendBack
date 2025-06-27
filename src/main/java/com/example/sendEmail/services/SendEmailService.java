package com.example.sendEmail.services;


import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;


public interface SendEmailService {

    MailResponse sendSimpleMail(MailRequest mailRequest);
    String buildHtmlBody(MailRequest mailRequest);
}
