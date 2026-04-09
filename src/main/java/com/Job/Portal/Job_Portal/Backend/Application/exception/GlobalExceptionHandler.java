package com.Job.Portal.Job_Portal.Backend.Application.exception;

import com.Job.Portal.Job_Portal.Backend.Application.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntime(RuntimeException ex) {
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),null
                ),
                HttpStatus.BAD_REQUEST

        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneral(Exception ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        "Something went wrong",null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
