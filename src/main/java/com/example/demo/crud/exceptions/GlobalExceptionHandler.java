package com.example.demo.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.models.ErrorDetail;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(
				ErrorDetail.builder().message(ex.getMessage()).origin(request.getDescription(false)).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> resourceBadRequestException(BadRequestException ex, WebRequest request) {
		List<Map<String, String>> details = ex.getErrors().stream().map(err -> {
			Map<String, String> detError = new HashMap<>();
			detError.put(err.getField(), err.getDefaultMessage());
			return detError;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(ErrorDetail.builder().message(ex.getMessage()).origin(request.getDescription(false))
				.details(details).build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		return new ResponseEntity<>(
				ErrorDetail.builder().message(ex.getMessage()).origin(request.getDescription(false)).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}