package com.example.sendEmail.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Value("${security.jwt.secret-key}")
    private String JwtSecret;

    @Value("${security.jwt.exipration-time}")
    private int JwtExpirationTime;


}
