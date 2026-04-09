package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.dto.JobResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.JobRepository;

import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    public Job createJob(Job job, String email) {
        logger.info("Job created by recruiter: {}", email);
        logger.error("Error while creating job");
        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        job.setRecruiter(recruiter);
        return jobRepository.save(job);
    }

    public List<JobResponse> getAllJobs() {
        logger.info("Fetching all jobs");
        return jobRepository.findAll().stream()

                .map(job -> new JobResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getCompanyName(),
                        job.getLocation(),
                        job.getRecruiter().getName()
                )).toList();
    }

    public List<Job> getMyJobs(String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jobRepository.findByRecruiter(recruiter);
    }
}
