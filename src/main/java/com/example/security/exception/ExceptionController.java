package com.example.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value= UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UserNotFoundException exception){
    	return new ResponseEntity<Object>("user not found", HttpStatus.NOT_FOUND);
    }
}
