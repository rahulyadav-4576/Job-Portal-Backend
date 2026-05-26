package com.Job.Portal.Job_Portal.Backend.Application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private T data;

}
