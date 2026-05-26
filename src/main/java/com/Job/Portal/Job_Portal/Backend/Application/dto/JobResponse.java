package com.Job.Portal.Job_Portal.Backend.Application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Long id;
    @NotBlank(message = "Title required")
    private String title;
    @NotBlank(message = "Company required")
    private String companyName;
    @NotBlank(message = "Location required")
    private String location;
    private String recruiterName;;


}
