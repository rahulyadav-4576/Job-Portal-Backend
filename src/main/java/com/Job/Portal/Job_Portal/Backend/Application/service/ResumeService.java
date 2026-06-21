package com.Job.Portal.Job_Portal.Backend.Application.service;

import com.Job.Portal.Job_Portal.Backend.Application.entity.User;
import com.Job.Portal.Job_Portal.Backend.Application.exception.ResourceNotFoundException;
import com.Job.Portal.Job_Portal.Backend.Application.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    public String uploadResume(
            MultipartFile file,
            String email
    ) {

        try {

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found"));

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "raw"
                    )
            );

            String resumeUrl =
                    uploadResult.get("secure_url").toString();

            user.setResumeUrl(resumeUrl);

            userRepository.save(user);

            return resumeUrl;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
}}
