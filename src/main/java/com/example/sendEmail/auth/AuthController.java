package com.example.sendEmail.auth;
import com.example.sendEmail.dtos.userDtos.*;
import com.example.sendEmail.models.UserModel;
import com.example.sendEmail.repository.UserRepository;
import com.example.sendEmail.services.impl.PasswordResetCodeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import com.example.sendEmail.security.JwtUtils;
import com.example.sendEmail.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordResetCodeServiceImpl passwordResetCodeService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService, UserRepository userRepository, PasswordResetCodeServiceImpl passwordResetCodeService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordResetCodeService = passwordResetCodeService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterRequest request) {
        UserModel user = userService.register(request.getName(), request.getEmail(), request.getPassword());
        String token = jwtUtils.generateToken(user.getEmail(),user.getName(),user.getPoints());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserModel user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String token = jwtUtils.generateToken(user.getEmail(),user.getName(),user.getPoints());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @GetMapping("/getClaims")
    public ResponseEntity<UserResponse> getClaims(HttpServletRequest request) {
        return ResponseEntity.ok(UserResponse.builder()
                        .name(jwtUtils.getAllClaimsFromToken(jwtUtils.extractTokenFromHeaders(request)).get("name", String.class))
                        .email(jwtUtils.getAllClaimsFromToken(jwtUtils.extractTokenFromHeaders(request)).get("email", String.class))
                        .points(jwtUtils.getAllClaimsFromToken(jwtUtils.extractTokenFromHeaders(request)).get("points", Integer.class))
                .build());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody EmailRequest request) {
        passwordResetCodeService.sendResetCode(request.getEmail());
        return ResponseEntity.ok("Password reset code sent");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeRequest request) {
        passwordResetCodeService.verifyResetCode(request.getEmail(), request.getCode());
        return ResponseEntity.ok("Code verified");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetCodeService.resetPassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok("Password was updated successfully");
    }



}
