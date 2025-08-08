package com.example.sendEmail.services;


import com.example.sendEmail.dtos.userDtos.AuthResponse;
import com.example.sendEmail.models.UserModel;

import java.util.List;

public interface UserService {


    UserModel register(String name, String email, String password);
}
