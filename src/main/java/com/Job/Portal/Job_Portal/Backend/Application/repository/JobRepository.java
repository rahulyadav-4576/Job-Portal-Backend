package com.Job.Portal.Job_Portal.Backend.Application.repository;

import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findByRecruiter(User recruiter);
//    List<Job> findByTitleContainingIgnoreCase(String title);
//    List<Job> findByLocationContainingIgnoreCase(String location);
}
