package com.Job.Portal.Job_Portal.Backend.Application.dto;

import com.Job.Portal.Job_Portal.Backend.Application.entity.ApplicationStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long id;
    private String userName;
    private String jobTitle;
    private String status;


}
