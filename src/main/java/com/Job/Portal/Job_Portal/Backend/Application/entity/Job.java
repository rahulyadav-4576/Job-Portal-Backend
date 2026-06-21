package com.Job.Portal.Job_Portal.Backend.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private String companyName;


    @ManyToOne
    @JoinColumn(name = "posted_by")
    @JsonIgnore
    private User recruiter;
    @OneToMany(mappedBy = "job")
    private List<Application> applications;



}
