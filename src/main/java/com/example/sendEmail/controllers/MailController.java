package com.example.sendEmail.controllers;

import com.example.sendEmail.dtos.ApiResponse;
import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;
import com.example.sendEmail.models.MailModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/email")
public interface MailController {

    @PostMapping("/send")
    ResponseEntity<ApiResponse<MailResponse>> sendEmail(@RequestBody MailRequest mailRequest);
    }
