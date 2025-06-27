package com.example.sendEmail.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailRequest {
    @NotBlank(message = "the name is required")
    private String name;
    @NotBlank(message = "the date is required")
    private String date;
    @NotBlank(message = "the email address is required")
    @Email(message = "the email should be valid")
    private String to;
    private String subject;
    private String reason;

}
