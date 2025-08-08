package com.example.sendEmail.controllers;

import com.example.sendEmail.dtos.ApiResponse;
import com.example.sendEmail.dtos.userDtos.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/users")
public interface UserController {
    @GetMapping("/getAll")
    ResponseEntity<ApiResponse<List<AuthResponse>>> getAllUsers();
}
