package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApiResponse;
import com.Job.Portal.Job_Portal.Backend.Application.dto.ApplicationResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Application;
import com.Job.Portal.Job_Portal.Backend.Application.entity.ApplicationStatus;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.ApplicationRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import com.Job.Portal.Job_Portal.Backend.Application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // ✅ Apply Job (USER)
    @PostMapping("/apply/{jobId}")
    public ApiResponse<String> applyJob(@PathVariable Long jobId,
                                Authentication authentication) {

        String email = authentication.getName();

        applicationService.applyJob(jobId, email);

        return new ApiResponse<>(
                true,
                "Applied successfully",
                null
        );
    }

    // ✅ Get My Applications (USER)
    @GetMapping("/my")
    public ApiResponse<List<ApplicationResponse>> myApplications(Authentication authentication) {

        String email = authentication.getName();

        return new ApiResponse<>(
                true,
                "Applications fetched",
                applicationService.getMyApplications(email)
        );
    }

    // ✅ Get Applications for a Job (RECRUITER)
    @GetMapping("/job/{jobId}")
    public  ApiResponse<List<ApplicationResponse>> jobApplications(@PathVariable Long jobId , Authentication authentication) {

        String email=authentication.getName();
        return new ApiResponse<>(
                true," Applications fetched",
                applicationService.getApplicationsByJob(jobId,email)
        );
    }
    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                applicationService.updateStatus(
                        applicationId,
                        status,
                        authentication.getName()
                )
        );
    }
}
