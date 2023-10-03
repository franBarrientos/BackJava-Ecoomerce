package com.treshermanitos.api.infrastructure.config.spring;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.treshermanitos.api.application.exceptions.BadRequest;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.exceptions.RelationshipAlreadyExist;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> internalError(Exception e, HttpServletRequest request) {
		return ApiResponse.serverError(e.getMessage());
	}@ExceptionHandler(NotFoundException.class)
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
