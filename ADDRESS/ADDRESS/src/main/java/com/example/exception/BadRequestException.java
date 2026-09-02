package com.example.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{

    String message;
    HttpStatus status;

    public BadRequestException(String message){
        super(message);
        this.message=message;
        this.status=HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
