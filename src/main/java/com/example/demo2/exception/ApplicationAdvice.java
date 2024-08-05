package com.example.demo2.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BadRequestErrorResponse> handleBindException(BindException e, HttpServletRequest request) {
        List<String> errors = getErrorsFromFieldErrors(e.getFieldErrors());
        BadRequestErrorResponse response = new BadRequestErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            request.getRequestURI(),
            "Bad Request",
            errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleBindException(MissingServletRequestParameterException e, HttpServletRequest request) {
        String message = e.getMessage();
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                HttpStatus.BAD_REQUEST.name(),
                request.getRequestURI(),
                message
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ServerExceptionResponse> applicationExceptionHandler(ApplicationException ex) {
        ServerExceptionResponse response = new ServerExceptionResponse(
                ex.getHttpStatus().value(),
                new Date(),
                ex.getCode(),
                ex.getMessage()
        );
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(response);
    }

    public record ServerExceptionResponse(int status, Date timestamp, String code, String message) {
    }

    public record BadRequestErrorResponse(int status, Date timestamp, String error, String path, List<String> errors) {
    }

    public record ErrorResponse(int status, Date timestamp, String error, String path, String message) {
    }

    private List<String> getErrorsFromFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
