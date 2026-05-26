package com.Job.Portal.Job_Portal.Backend.Application.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthRequest {
    private String email;
    private String password;


}
