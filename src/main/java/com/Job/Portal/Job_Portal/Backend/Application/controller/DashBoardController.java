package com.Job.Portal.Job_Portal.Backend.Application.controller;

import com.Job.Portal.Job_Portal.Backend.Application.dto.DashBoardDTO.RecruiterDashBoardResponse;
import com.Job.Portal.Job_Portal.Backend.Application.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final DashBoardService dashBoardService;
    @GetMapping("/recruiter")
    public RecruiterDashBoardResponse getDashboard(
            Authentication authentication
    ) {

        return dashBoardService.getRecruiterDashBoard(
                authentication.getName()
        );
    }
}
