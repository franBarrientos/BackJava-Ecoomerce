package com.treshermanitos.treshermanitos.config;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.treshermanitos.treshermanitos.exceptions.BadRequest;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.exceptions.RelationshipAlreadyExist;

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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> badRequestNotValidated(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		return ApiResponse.badRequest(
				e.getBindingResult().getFieldErrors().stream()
						.collect(Collectors.toMap(k -> k.getField(), v -> v.getDefaultMessage())));
	}

	@ExceptionHandler(RelationshipAlreadyExist.class)
	public ResponseEntity<ApiResponse> relationshipAlreadyExist(RelationshipAlreadyExist e, HttpServletRequest request) {
		return ApiResponse.badRequest(e.getMessage());
	}

}
