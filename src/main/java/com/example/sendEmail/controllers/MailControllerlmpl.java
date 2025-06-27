package com.example.sendEmail.controllers;

import com.example.sendEmail.dtos.ApiResponse;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;
import com.example.sendEmail.services.SendEmailServicelmpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailControllerlmpl implements MailController {

    private SendEmailServicelmpl sendEmailService;
    public MailControllerlmpl(SendEmailServicelmpl sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Override
    public ResponseEntity<ApiResponse<MailResponse>> sendEmail(@RequestBody @Valid MailRequest mailRequest){

        MailResponse response = sendEmailService.sendSimpleMail(mailRequest);

        ApiResponse<MailResponse> apiResponse = ApiResponse.<MailResponse>builder()
                .message("The email is sent successfully to the user below")
                .data(response)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
