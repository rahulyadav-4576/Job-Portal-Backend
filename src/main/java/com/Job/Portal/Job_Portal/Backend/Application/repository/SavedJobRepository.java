package com.Job.Portal.Job_Portal.Backend.Application.repository;

import com.Job.Portal.Job_Portal.Backend.Application.entity.Job;
import com.Job.Portal.Job_Portal.Backend.Application.entity.SavedJob;
import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob,Long> {
    List<SavedJob> findByUser(User user);

    Optional<SavedJob> findByUserAndJob(
            User user,
            Job job
    );
}
