package com.takebook.demo.exception;

public abstract class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
