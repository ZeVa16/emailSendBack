package com.example.sendEmail.services.impl;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;
import com.example.sendEmail.services.SendEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.util.List;

@Service
public class SendEmailServiceImpl implements SendEmailService {

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
            helper.setSubject("Confirmacion de cita");
            helper.setFrom("2022260@utsh.edu.mx");
            helper.setText(buildHtmlBody(mailRequest), true);

            mailSender.send(message);
            sendToTeam(mailRequest);

            return new MailResponse(mailRequest.getName(),mailRequest.getTo());
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email",e);

        }
    }

    @Override
    public void sendToTeam(MailRequest mailRequest){
        try{
            List<String> teamMembers = List.of(
                    "2022221@utsh.edu.mx",
                    "2022260@utsh.edu.mx",
                    "2022578@utsh.edu.mx",
                    "2021613@utsh.edu.mx"
                    );
            for (String teamMember : teamMembers){
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,true);
                helper.setTo(teamMember);
                helper.setSubject("Confirmacion de cita");
                helper.setFrom("2022260@utsh.edu.mx");
                helper.setText(buildHtmlBodyTeam(mailRequest), true);
                mailSender.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sendind the mail to the team members",e);
        }

    }

    @Override
    public String buildHtmlBody(MailRequest mailRequest) {
        Context context = new Context();
        context.setVariable("name", mailRequest.getName());
        context.setVariable("date", mailRequest.getDate());
        context.setVariable("reason", mailRequest.getReason());
        return templateEngine.process("user-mail-template", context);

    }

    @Override
    public String buildHtmlBodyTeam(MailRequest mailRequest){
        Context context = new Context();
        context.setVariable("name", mailRequest.getName());
        context.setVariable("date", mailRequest.getDate());
        context.setVariable("reason", mailRequest.getReason());
        return templateEngine.process("team-mail-template", context);
    }


}
