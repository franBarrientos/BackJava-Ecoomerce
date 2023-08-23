package com.treshermanitos.treshermanitos.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.treshermanitos.treshermanitos.exceptions.BadRequest;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<ApiResponse> notFound(NotFoundException e, HttpServletRequest request) {
	        return ApiResponse.notFound(e.getMessage());
	    }
    @ExceptionHandler(BadRequest.class)
	    public ResponseEntity<ApiResponse> badRequest(NotFoundException e, HttpServletRequest request) {
	        return ApiResponse.badRequest(e.getMessage());
	    }
}
