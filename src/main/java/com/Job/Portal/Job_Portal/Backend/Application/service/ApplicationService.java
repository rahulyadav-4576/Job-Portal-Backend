package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApplicationResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Application;
import com.Job.Portal.Job_Portal.Backend.Application.entity.ApplicationStatus;
import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.ApplicationRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.JobRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    public Application applyJob(Long jobId, String email) {
        logger.info("User {} applying for job {}", email, jobId);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.findByUserAndJob(user, job).isPresent()) {
            throw new RuntimeException("Already applied!");
        }

        Application app = Application.builder()
                .user(user)
                .job(job)
                .status(ApplicationStatus.APPLIED)
                .build();
        return applicationRepository.save(app);
    }
    public List<ApplicationResponse> getMyApplications(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByUser(user)
                .stream()
                .map(app -> new ApplicationResponse(
                        app.getId(),
                        app.getUser().getName(),
                        app.getJob().getTitle(),
                        app.getStatus().name()
                ))
                .toList();
    }

    public List<ApplicationResponse> getApplicationsByJob(Long jobId,String email) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (!job.getRecruiter().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }


        return applicationRepository.findByJob(job)
                .stream()
                .map(app -> new ApplicationResponse(
                        app.getId(),
                        app.getUser().getName(),
                        app.getJob().getTitle(),
                        app.getStatus().name()
                ))
                .toList();
    }
    public void updateStatus(Long appId, ApplicationStatus status) {

        Application app = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        app.setStatus(status);

        applicationRepository.save(app);
    }
}
