package com.Job.Portal.Job_Portal.Backend.Application.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
