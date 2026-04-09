package com.Job.Portal.Job_Portal.Backend.Application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;//USER  or  RECRUITER
}
