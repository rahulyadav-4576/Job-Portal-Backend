package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.SavedJob;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.JobRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.SavedJobRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedJobService {
    private final SavedJobRepository savedJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public void saveJob(Long jobId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (savedJobRepository.findByUserAndJob(user, job).isPresent()) {
            throw new RuntimeException("Job already saved");
        }

        SavedJob savedJob = SavedJob.builder()
                .user(user)
                .job(job)
                .build();

        savedJobRepository.save(savedJob);
    }
}
