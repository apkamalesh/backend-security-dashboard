package com.example.hcl_project.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}
