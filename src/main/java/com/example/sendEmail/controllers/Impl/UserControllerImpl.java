package com.example.sendEmail.controllers.Impl;

import com.example.sendEmail.controllers.UserController;
import com.example.sendEmail.dtos.ApiResponse;
import com.example.sendEmail.dtos.userDtos.AuthResponse;
import com.example.sendEmail.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl {

    private final UserService userService;
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

//    @Override
//    public ResponseEntity<ApiResponse<List<AuthResponse>>> getAllUsers(){
//        List<AuthResponse> users = userService.getAllUsers();
//        ApiResponse<List<AuthResponse>> apiResponse = ApiResponse.<List<AuthResponse>>builder()
//                .message("Users obtainerd succesfully")
//                .data(users)
//                .status(HttpStatus.OK)
//                .build();
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//
//
//    }




}
