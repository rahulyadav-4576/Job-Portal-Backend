package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.dto.JobResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.exception.ResourceNotFoundException;
import com.Job.Portal.Job_Portal.Backend.Application.repository.JobRepository;

import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(JobService.class);

    public Job createJob(Job job, String email) {

        logger.info("Recruiter {} is creating a job", email);

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new ResourceNotFoundException("User not found");
                });

        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        logger.info("Job created successfully with id: {}", savedJob.getId());

        return savedJob;
    }

    public List<JobResponse> getAllJobs() {

        logger.info("Fetching all jobs");

        List<JobResponse> jobs = jobRepository.findAll()
                .stream()
                .map(job -> new JobResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getCompanyName(),
                        job.getLocation(),
                        job.getRecruiter().getName()
                ))
                .toList();

        logger.info("Total jobs fetched: {}", jobs.size());

        return jobs;
    }
    public Page<JobResponse> searchJobs(
            String keyword,
            int page,
            int size,
            String sortBy
    ) {

        logger.info("Searching jobs with keyword: {}", keyword);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        Page<JobResponse> jobs =
                jobRepository
                        .findByTitleContainingIgnoreCase(
                                keyword,
                                pageable
                        )
                        .map(job -> new JobResponse(
                                job.getId(),
                                job.getTitle(),
                                job.getCompanyName(),
                                job.getLocation(),
                                job.getRecruiter().getName()
                        ));

        logger.info("Search completed successfully");

        return jobs;
    }

    public List<Job> getMyJobs(String email) {

        logger.info("Fetching jobs for recruiter: {}", email);

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Recruiter not found with email: {}", email);
                    return new ResourceNotFoundException("User not found");
                });

        List<Job> recruiterJobs =
                jobRepository.findByRecruiter(recruiter);

        logger.info("Total jobs found: {}", recruiterJobs.size());

        return recruiterJobs;
    }
    public Job updateJob(Long jobId, Job updatedJob, String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        if (!existingJob.getRecruiter().getId()
                .equals(recruiter.getId())) {
            throw new RuntimeException(
                    "You can update only your own jobs"
            );
        }

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setCompanyName(updatedJob.getCompanyName());

        return jobRepository.save(existingJob);
    }
    public void deleteJob(Long jobId, String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        if (!job.getRecruiter().getId()
                .equals(recruiter.getId())) {
            throw new RuntimeException(
                    "You can delete only your own jobs"
            );
        }

        jobRepository.delete(job);
    }
    public Job getJobById(Long jobId) {

        return jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
    }
}
