package com.Job.Portal.Job_Portal.Backend.Application.repository;

import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    Page<Job> findByTitleContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
    List<Job> findByRecruiter(User recruiter);
    long countByRecruiter(User recruiter);
    List<Job> findByTitleContainingIgnoreCase(String keyword);

    List<Job> findByLocationContainingIgnoreCase(String location);

    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);
}
