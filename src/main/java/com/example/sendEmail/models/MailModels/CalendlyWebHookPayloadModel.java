package com.example.sendEmail.models.MailModels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendlyWebHookPayloadModel {
    private String event;
    private PayLoad payload;

}

