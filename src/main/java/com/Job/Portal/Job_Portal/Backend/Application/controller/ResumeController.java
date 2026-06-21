package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume( @RequestParam("file") MultipartFile file,
                                           Authentication authentication){
        String email=authentication.getName();
        String url=resumeService.uploadResume(file,email);
        return ResponseEntity.ok(url);
    }
}
