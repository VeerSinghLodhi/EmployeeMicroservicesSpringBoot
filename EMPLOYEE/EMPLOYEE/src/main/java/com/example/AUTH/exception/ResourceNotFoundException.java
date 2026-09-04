package com.example.AUTH.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{

    String message;
    HttpStatus status;

    public ResourceNotFoundException(String message){
        super(message);
        this.message=message;
        this.status=HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
