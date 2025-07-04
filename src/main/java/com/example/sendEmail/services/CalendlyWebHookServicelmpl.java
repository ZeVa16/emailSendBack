package com.example.sendEmail.services;

import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.models.CalendlyWebHookPayloadModel;
import com.example.sendEmail.models.QuestionAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendlyWebHookServicelmpl implements CalendlyWebHookService {

    private final SendEmailService sendEmailService;

    public CalendlyWebHookServicelmpl(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Override
    public void processWebhook(CalendlyWebHookPayloadModel webhookPayload) {
        if (webhookPayload == null || webhookPayload.getPayload() == null) return;

        String name = webhookPayload.getPayload().getName();
        String email = webhookPayload.getPayload().getEmail();
        String date = webhookPayload.getPayload().getCreated_at();

        String reason = "";
        List<QuestionAnswer> qna = webhookPayload.getPayload().getQuestionAnswerList();
        if (qna != null) {
            for (QuestionAnswer qa : qna) {
                if (qa.getQuestion().toLowerCase().contains("motivo") ||
                        qa.getQuestion().toLowerCase().contains("razón")) {
                    reason = qa.getAnswer();
                    break;
                }
            }
        }

        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(email);
        mailRequest.setName(name);
        mailRequest.setDate(date);
        mailRequest.setReason(reason);

        sendEmailService.sendSimpleMail(mailRequest);
    }
}
