package com.example.sendEmail.services.impl;

import com.example.sendEmail.models.PasswordResetCode;
import com.example.sendEmail.models.UserModel;
import com.example.sendEmail.repository.PasswordResetCodeRepository;
import com.example.sendEmail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetCodeServiceImpl {

    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void sendResetCode(String email){
        Optional<UserModel> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            throw new RuntimeException("User not found");
        }
        String code = String.format("%06d",new Random().nextInt(999999));

        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setEmail(email);
        passwordResetCode.setCode(code);
        passwordResetCode.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        passwordResetCode.setUsed(false);
        passwordResetCodeRepository.save(passwordResetCode);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Código de recuperación");
        message.setText("Tu código para restablecer la contraseña es: " + code + "\nEste código expira en 10 minutos.");
        javaMailSender.send(message);
    }

    public void verifyResetCode(String email,String code){
        PasswordResetCode passwordResetCode = passwordResetCodeRepository
                .findTopByEmailAndCodeOrderByExpirationTimeDesc(email,code)
                .orElseThrow(() -> new RuntimeException("invalid code"));
        if(passwordResetCode.isUsed()){
            throw new RuntimeException("Code has been used");
        }
        if(passwordResetCode.getExpirationTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Code has expired");
        }
        passwordResetCode.setUsed(true);
        passwordResetCodeRepository.save(passwordResetCode);
    }

    public void resetPassword(String email,String newPassword){
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
