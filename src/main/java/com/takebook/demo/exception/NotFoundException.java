package com.takebook.demo.exception;

public class NotFoundException extends APIException {

    public NotFoundException(String message) {
        super(message);
    }
}
