package com.example.sendEmail.services;

import com.example.sendEmail.models.MailModels.CalendlyWebHookPayloadModel;

public interface CalendlyWebHookService {
    void processWebhook(CalendlyWebHookPayloadModel webhookPayload);
}
