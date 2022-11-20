package com.takebook.demo.controller;

import com.takebook.demo.exception.APIException;
import com.takebook.demo.model.exception.ExceptionCause;
import com.takebook.demo.model.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleNotFoundValue(APIException apiException) {
        return ExceptionDetails.builder()
                .message(apiException.getMessage())
                .cause(ExceptionCause.NOT_FOUND_VALUE)
                .build();
    }
}
