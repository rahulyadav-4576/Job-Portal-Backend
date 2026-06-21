package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApiResponse;
import com.Job.Portal.Job_Portal.Backend.Application.dto.JobResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
@Tag(name = "Job APIs", description = "Operations related to jobs")
public class JobController {
    private final JobService jobService;

    // Recruiter: Create Job
    @PostMapping
    @Operation(summary = "Create a new job")
    public Job createJob(@RequestBody Job job, Authentication authentication) {
        String email = authentication.getName();
        return jobService.createJob(job, email);
    }

    // User: Get All Jobs

    @GetMapping
    @Operation(summary = "Get all jobs")
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

    @GetMapping("/search")
    public ApiResponse<Page<JobResponse>> searchJobs(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy
    ) {

        return new ApiResponse<>(
                true,
                "Jobs fetched successfully",
                jobService.searchJobs(
                        keyword,
                        page,
                        size,
                        sortBy
                )
        );
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(
            @PathVariable Long jobId,
            @RequestBody Job job,
            Authentication authentication
    ) {

        String email = authentication.getName();

        Job updatedJob =
                jobService.updateJob(
                        jobId,
                        job,
                        email
                );

        return ResponseEntity.ok(updatedJob);}
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable("jobId") Long jobId,
            Authentication authentication
    ) {

        jobService.deleteJob(
                jobId,
                authentication.getName()
        );

        return ResponseEntity.ok(
                "Job deleted successfully"
        );
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(
            @PathVariable("jobId") Long jobId
    ) {

        return ResponseEntity.ok(
                jobService.getJobById(jobId)
        );
    }
}
