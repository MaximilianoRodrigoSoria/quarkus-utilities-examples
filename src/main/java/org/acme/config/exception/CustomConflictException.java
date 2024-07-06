package org.acme.config.exception;

public class CustomConflictException extends RuntimeException {
    public CustomConflictException(String message) {
        super(message);
    }
}