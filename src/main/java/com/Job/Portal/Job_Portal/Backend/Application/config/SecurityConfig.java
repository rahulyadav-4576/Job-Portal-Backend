package com.Job.Portal.Job_Portal.Backend.Application.config;

import com.Job.Portal.Job_Portal.Backend.Application.security.JwtFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https.csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/job/**"
                        ).hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/job/**"
                        ).hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/job/**"
                        ).hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/job/**"
                        ).permitAll()

                        .requestMatchers(
                                "/applications/apply/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                "/applications/job/**"
                        ).hasRole("RECRUITER")

                        .requestMatchers(
                                "/dashboard/**"
                        ).hasRole("RECRUITER")

                        .requestMatchers("/savedjob/**"

                        ).hasRole("USER")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return https.build();

    }
}

