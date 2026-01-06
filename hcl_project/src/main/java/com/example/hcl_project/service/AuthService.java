package com.example.hcl_project.service;

import com.example.hcl_project.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public SignupResponse signup(SignupRequest req) {
        return new SignupResponse(true, "Signup successful");
    }

    public LoginResponse login(LoginRequest req) {
        return new LoginResponse(true, "Login successful");
    }
}
