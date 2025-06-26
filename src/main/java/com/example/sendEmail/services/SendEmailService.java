package com.example.sendEmail.services;
import com.example.sendEmail.models.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(MailModel mailModel) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailModel.getTo());
        message.setSubject(mailModel.getSubject());
        message.setText(mailModel.getBody());
        message.setFrom("2022260@utsh.edu.mx");

        mailSender.send(message);

    }
}
