package ar.com.laboratory.config.exception;

public class CustomConflictException extends RuntimeException {
    public CustomConflictException(String message) {
        super(message);
    }
}