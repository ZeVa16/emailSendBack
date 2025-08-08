package com.example.sendEmail.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequest {
    private String name;
    private String password;
    private String email;

}
