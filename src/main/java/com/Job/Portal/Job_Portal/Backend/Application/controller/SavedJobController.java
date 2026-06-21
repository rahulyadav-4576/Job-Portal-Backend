package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApiResponse;
import com.Job.Portal.Job_Portal.Backend.Application.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/savedjob")
@RequiredArgsConstructor
public class SavedJobController {
    private final SavedJobService savedJobService;
    @PostMapping("/{jobId}")
    public ApiResponse<String> saveJob(
            @PathVariable Long jobId,
            Authentication authentication
    ) {

        savedJobService.saveJob(
                jobId,
                authentication.getName()
        );

        return new ApiResponse<>(
                true,
                "Job saved successfully",
                null
        );
    }

}
