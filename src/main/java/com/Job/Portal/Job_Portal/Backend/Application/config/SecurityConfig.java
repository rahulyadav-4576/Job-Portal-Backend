package com.Job.Portal.Job_Portal.Backend.Application.config;

import com.Job.Portal.Job_Portal.Backend.Application.security.JwtFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https)throws Exception{
        https
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/job").hasRole("RECRUITER") // create job
                        .requestMatchers("/job/my").hasRole("RECRUITER")

                        .requestMatchers("/applications/apply/{jobId}/**").hasRole("USER")

                        .requestMatchers("/applications/job/**").hasRole("RECRUITER")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return https.build();

    }
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
}
