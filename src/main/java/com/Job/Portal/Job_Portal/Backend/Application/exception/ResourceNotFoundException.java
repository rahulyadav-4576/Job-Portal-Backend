package com.Job.Portal.Job_Portal.Backend.Application.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
