package com.example.sendEmail.dtos;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class MailResponse {

    private String name;
    private String to;

}
