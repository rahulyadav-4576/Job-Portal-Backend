package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApiResponse;
import com.Job.Portal.Job_Portal.Backend.Application.dto.JobResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    // Recruiter: Create Job
    @PostMapping
    public Job createJob(@RequestBody Job job, Authentication authentication) {
        String email = authentication.getName();
        return jobService.createJob(job, email);
    }

    // User: Get All Jobs

    @GetMapping
    public ApiResponse<List<JobResponse>> getAllJobs() {
        return new ApiResponse<>(
                true,
                "Jobs fetched successfully",jobService.getAllJobs()
        ); 
    }
    // Recruiter: Get My Jobs
    @GetMapping("/my")
    public List<Job> getMyJobs(Authentication authentication) {
        String email = authentication.getName();
        return jobService.getMyJobs(email);
    }
}
