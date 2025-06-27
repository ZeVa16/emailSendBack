package com.example.sendEmail.services;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class SendEmailServicelmpl implements SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public MailResponse sendSimpleMail(MailRequest mailRequest) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailRequest.getTo());
            helper.setSubject(mailRequest.getSubject());
            helper.setFrom("2022260@utsh.edu.mx");
            helper.setText(buildHtmlBody(mailRequest), true);

            mailSender.send(message);

            return new MailResponse(mailRequest.getName(),mailRequest.getTo());
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email",e);

        }
    }
    @Override
    public String buildHtmlBody(MailRequest mailRequest) {
        Context context = new Context();
        context.setVariable("name", mailRequest.getName());
        context.setVariable("lastName", mailRequest.getLastName());
        context.setVariable("date", mailRequest.getDate());
        return templateEngine.process("user-mail-template", context);

    }


}
