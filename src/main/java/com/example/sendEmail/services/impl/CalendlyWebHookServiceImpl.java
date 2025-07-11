package com.example.sendEmail.services.impl;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.models.CalendlyWebHookPayloadModel;
import com.example.sendEmail.models.QuestionAnswer;
import com.example.sendEmail.services.CalendlyWebHookService;
import com.example.sendEmail.services.SendEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CalendlyWebHookServiceImpl implements CalendlyWebHookService {

    @Value("${calendly.api.token}")
    private String TOKEN;

    private final RestTemplate restTemplate = new RestTemplate();
    private final SendEmailService sendEmailService;


    public CalendlyWebHookServiceImpl(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Override
    public void processWebhook(CalendlyWebHookPayloadModel webhookPayload) {
        if (webhookPayload == null || webhookPayload.getPayload() == null) return;

        String name = webhookPayload.getPayload().getName();
        String email = webhookPayload.getPayload().getEmail();
        String eventUrl = webhookPayload.getPayload().getEvent();
        String date = getDateFromCalendly(eventUrl);
        String reason = getReasonFromCalendly(webhookPayload);

        MailRequest mailRequest = buildMailRequest(name, email, date, reason);
        sendEmailService.sendSimpleMail(mailRequest);
    }

    private String getDateFromCalendly(String eventUrl){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", ("Bearer "+TOKEN));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(
                    eventUrl,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );
            Map<String, Object> resource = (Map<String, Object>) response.getBody().get("resource");
            return (String) resource.get("start_time");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    };

    private String getReasonFromCalendly(CalendlyWebHookPayloadModel webhookPayload){
        List<QuestionAnswer> qna = webhookPayload.getPayload().getQuestionAnswerList();
        if (qna != null) {
            for (QuestionAnswer qa : qna) {
                if (qa.getQuestion().equalsIgnoreCase("Please share anything that will help prepare for our meeting.")) {
                    return qa.getAnswer();
                }
            }
        }
        return null;
    };

    private MailRequest buildMailRequest(String name, String email, String date, String reason){
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(email);
        mailRequest.setName(name);
        mailRequest.setDate(date);
        mailRequest.setReason(reason);
        return mailRequest;
    }
}

