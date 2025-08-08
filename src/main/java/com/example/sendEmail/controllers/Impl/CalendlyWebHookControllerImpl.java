package com.example.sendEmail.controllers.Impl;

import com.example.sendEmail.controllers.CalendlyWebHookController;
import com.example.sendEmail.models.MailModels.CalendlyWebHookPayloadModel;
import com.example.sendEmail.services.CalendlyWebHookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CalendlyWebHookControllerImpl implements CalendlyWebHookController {

    private final CalendlyWebHookService webHookService;

    public CalendlyWebHookControllerImpl(CalendlyWebHookService webHookService) {
        this.webHookService = webHookService;
    }
    @Override
    public ResponseEntity<String> receiveWebhook(@RequestBody CalendlyWebHookPayloadModel webhookPayload) {
        webHookService.processWebhook(webhookPayload);
        return ResponseEntity.ok("Data received successfully ");
    }
}
