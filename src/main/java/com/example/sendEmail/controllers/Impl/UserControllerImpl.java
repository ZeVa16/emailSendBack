package com.example.sendEmail.controllers.Impl;

import com.example.sendEmail.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl {

    private final UserService userService;
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }




}
