package com.example.sendEmail.services;


import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;


public interface SendEmailService {

    MailResponse sendSimpleMail(MailRequest mailRequest);

    void sendToTeam(MailRequest mailRequest);

    String buildHtmlBody(MailRequest mailRequest);

    String buildHtmlBodyTeam(MailRequest mailRequest);
}
