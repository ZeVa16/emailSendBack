package com.example.sendEmail.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailRequest {

    private String name;
    private String lastName;
    private String date;
    private String to;
    private String subject;
    private String body;

}
