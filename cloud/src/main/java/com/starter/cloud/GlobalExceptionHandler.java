package com.starter.cloud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<UserResponse> handleInvalidUser(InvalidUserException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new UserResponse(ex.getMessage(), "FAIL"));
    }
    
}
