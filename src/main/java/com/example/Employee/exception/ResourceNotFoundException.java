package com.example.Employee.exception;

import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.ResponseStatus;

// This annotation will automatically send a 404 Not Found HTTP status
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;


    // Constructor that accepts a message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}