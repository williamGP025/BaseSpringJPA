package com.example.demo.crud.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@Builder
@Getter
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 2L;
    private List<FieldError> errors;
    private String message;
}