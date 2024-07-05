package com.marcoant.salesforce_api.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        String responseMessage = e.getMessage();
        if (e.getDetailedMessage() != null) {
            responseMessage += " " + e.getDetailedMessage();
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(e.getHttpStatus()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations()
                                .stream()
                                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                                .collect(Collectors.joining(", "));
        return new ResponseEntity<>("Ops.. ocorreu um erro de validação: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                                         .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                                         .collect(Collectors.joining(", "));
        return new ResponseEntity<>("Ops.. ocorreu um erro de validação: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        return new ResponseEntity<>("Ops.. ocorreu um erro interno. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
