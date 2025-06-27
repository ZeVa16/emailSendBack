package com.example.sendEmail.models;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MailModel {

    private String name;
    private String date;
    private String to;
    private String subject;
    private String reason;

}
