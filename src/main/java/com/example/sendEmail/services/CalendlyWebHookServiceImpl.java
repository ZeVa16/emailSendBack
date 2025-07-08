package com.example.sendEmail.services;

import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.models.CalendlyWebHookPayloadModel;
import com.example.sendEmail.models.QuestionAnswer;
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

    private final String TOKEN = "eyJraWQiOiIxY2UxZTEzNjE3ZGNmNzY2YjNjZWJjY2Y4ZGM1YmFmYThhNjVlNjg0MDIzZjdjMzJiZTgzNDliMjM4MDEzNWI0IiwidHlwIjoiUEFUIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJodHRwczovL2F1dGguY2FsZW5kbHkuY29tIiwiaWF0IjoxNzUxNDgyODgzLCJqdGkiOiI4ZTNhYmIzNS0zODM2LTQ3MzUtYmFiNi1jMjEwYjUyZjg2NDUiLCJ1c2VyX3V1aWQiOiJlOTRmNDFjOC02NzRjLTRhYmQtYmVhMi1mNmVlYzBkNTAwYWMifQ.VfOOidMRTkf0aqXV67_GWWzC6F7rS75T6x2vRDmguwy6lMc_eM1dDv8bUPmqgej2s03a-gsREBP6FMs4qhRkmw";
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

