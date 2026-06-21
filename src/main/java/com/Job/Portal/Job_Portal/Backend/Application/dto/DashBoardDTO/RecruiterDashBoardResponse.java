package com.Job.Portal.Job_Portal.Backend.Application.dto.DashBoardDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecruiterDashBoardResponse {
    private Long totalJobs;
    private Long totalApplications;
    private Long pendingApplications;
    private Long shortlistedApplications;
    private Long rejectedApplications;
    private Long hiredApplications;

}
