package com.example.sendEmail.services.impl;
import com.example.sendEmail.dtos.userDtos.UserResponse;
import com.example.sendEmail.models.UserModel;
import com.example.sendEmail.repository.UserRepository;
import com.example.sendEmail.services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers(){
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getName(),user.getPoints()))
                .collect(Collectors.toList());
    }


}
