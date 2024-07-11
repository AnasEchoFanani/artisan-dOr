package com.app.artisandor.exception;

public class CommandeValidationException extends RuntimeException {
    public CommandeValidationException(String message) {
        super(message);
    }
}
