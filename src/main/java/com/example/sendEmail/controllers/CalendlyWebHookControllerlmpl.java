package com.example.sendEmail.controllers;

import com.example.sendEmail.models.CalendlyWebHookPayloadModel;
import com.example.sendEmail.services.CalendlyWebHookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CalendlyWebHookControllerlmpl implements CalendlyWebHookController {

    private final CalendlyWebHookService webHookService;

    public CalendlyWebHookControllerlmpl(CalendlyWebHookService webHookService) {
        this.webHookService = webHookService;
    }
    @Override
    public ResponseEntity<String> receiveWebhook(@RequestBody CalendlyWebHookPayloadModel webhookPayload) {
        webHookService.processWebhook(webhookPayload);
        return ResponseEntity.ok("Webhook procesado correctamente");
    }
}
