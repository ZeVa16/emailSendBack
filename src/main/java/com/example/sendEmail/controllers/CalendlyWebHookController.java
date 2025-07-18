package com.example.sendEmail.controllers;

import com.example.sendEmail.models.CalendlyWebHookPayloadModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/calendly")
public interface CalendlyWebHookController {

    @PostMapping("/webhook")
    ResponseEntity<String> receiveWebhook(@RequestBody CalendlyWebHookPayloadModel webhookPayload);

}
