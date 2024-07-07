package ar.com.laboratory.config.exception;

public class MaxRetriesException extends Exception {
    public MaxRetriesException() {
        super("Se excedieron el número de reintentos");
    }
}