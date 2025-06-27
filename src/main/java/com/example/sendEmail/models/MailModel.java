package com.example.sendEmail.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailModel {

    private String name;
    private String lastName;
    private String date;
    private String to;
    private String subject;

}
