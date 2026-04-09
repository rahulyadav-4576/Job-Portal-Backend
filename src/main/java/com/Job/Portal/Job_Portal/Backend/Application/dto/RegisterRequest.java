package com.Job.Portal.Job_Portal.Backend.Application.dto;

import com.Job.Portal.Job_Portal.Backend.Application.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    private Role role;
}
