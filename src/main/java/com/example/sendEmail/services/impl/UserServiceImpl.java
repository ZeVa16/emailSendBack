package com.example.sendEmail.services.impl;
import com.example.sendEmail.models.UserModel;
import com.example.sendEmail.repository.UserRepository;
import com.example.sendEmail.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserModel register(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }
        UserModel user = UserModel.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .points(0)
                .build();
        return userRepository.save(user);
    }


}
