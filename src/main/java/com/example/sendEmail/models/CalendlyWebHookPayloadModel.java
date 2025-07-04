package com.example.sendEmail.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendlyWebHookPayloadModel {
    private String event;
    private PayLoad payload;

}

