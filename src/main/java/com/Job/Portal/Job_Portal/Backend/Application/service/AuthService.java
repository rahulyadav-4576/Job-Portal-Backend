package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.dto.AuthRequest;
import com.Job.Portal.Job_Portal.Backend.Application.dto.RegisterRequest;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import com.Job.Portal.Job_Portal.Backend.Application.security.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    public String register(RegisterRequest request){
        logger.info("Registration attempt for email: {}", request.getEmail());

        // Check if user already exists
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            logger.warn("Registration failed - Email already exists: {}", request.getEmail());
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        logger.info("User registered successfully: {}", request.getEmail());
        return "User Registered Successfully";
    }
    public String login(AuthRequest request){
        logger.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login failed - User not found: {}", request.getEmail());
                    return new RuntimeException("User Not Found");
                });

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            logger.warn("Login failed - Invalid password for: {}", request.getEmail());
            throw new RuntimeException("Invalid Password");
        }

        logger.info("Login successful for: {}", request.getEmail());
        return jwtService.generateToken(user.getEmail());
    }
}
