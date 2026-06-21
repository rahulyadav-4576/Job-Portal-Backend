package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.dto.DashBoardDTO.RecruiterDashBoardResponse;
import com.Job.Portal.Job_Portal.Backend.Application.entity.ApplicationStatus;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.repository.ApplicationRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.JobRepository;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public RecruiterDashBoardResponse getRecruiterDashBoard(String email){
        User recruiter=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        return new RecruiterDashBoardResponse(
                jobRepository.countByRecruiter(recruiter),
                applicationRepository.countByJobRecruiter(recruiter),
                applicationRepository.countByJobRecruiterAndStatus(
                        recruiter,
                        ApplicationStatus.PENDING
                ),
                applicationRepository.countByJobRecruiterAndStatus(
                        recruiter,
                        ApplicationStatus.SHORTLISTED
                ),

                applicationRepository.countByJobRecruiterAndStatus(
                        recruiter,
                        ApplicationStatus.REJECTED
                ),

                applicationRepository.countByJobRecruiterAndStatus(
                        recruiter,
                        ApplicationStatus.HIRED
                )
        );

    }
}
