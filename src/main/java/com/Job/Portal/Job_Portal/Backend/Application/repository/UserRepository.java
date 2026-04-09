package com.Job.Portal.Job_Portal.Backend.Application.repository;

import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
