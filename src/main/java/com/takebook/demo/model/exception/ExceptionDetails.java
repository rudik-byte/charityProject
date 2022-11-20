package com.takebook.demo.model.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ExceptionDetails {
    protected final String message;
    protected final ExceptionCause cause;
}
