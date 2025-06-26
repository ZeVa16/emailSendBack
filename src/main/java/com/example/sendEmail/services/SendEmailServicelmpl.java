package com.example.sendEmail.services;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServicelmpl implements SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public MailResponse sendSimpleMail(MailRequest mailRequest) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getTo());
        message.setSubject(mailRequest.getSubject());
        message.setText(buildBody(mailRequest));
        message.setFrom("2022260@utsh.edu.mx");

        mailSender.send(message);

        return new MailResponse(mailRequest.getName(), mailRequest.getTo());
    }

    @Override
    public String buildBody(MailRequest mailRequest) {
        return String.format("""
        Hola %s %s,

        Te confirmamos tu cita para el día %s.

        %s

        Saludos,
        Equipo de Soporte
        """,
                mailRequest.getName(),
                mailRequest.getLastName(),
                mailRequest.getDate(),
                mailRequest.getBody()
        );
    }

}
