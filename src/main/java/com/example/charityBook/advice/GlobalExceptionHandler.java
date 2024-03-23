package com.example.charityBook.advice;

import com.example.charityBook.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException ex) {
        return createResponseEntity(ex, "Entity not found", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(RuntimeException ex, String errorMessage, HttpStatus status) {
        log.error(errorMessage, ex);
        ErrorResponse exception = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(exception, status);
    }
}
