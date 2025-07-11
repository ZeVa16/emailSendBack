package com.example.sendEmail.services;


import com.example.sendEmail.dtos.userDtos.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
}
