package com.example.demo.crud.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}