package com.Job.Portal.Job_Portal.Backend.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long id;
    private String userName;
    private String jobTitle;
    private String status;
}
