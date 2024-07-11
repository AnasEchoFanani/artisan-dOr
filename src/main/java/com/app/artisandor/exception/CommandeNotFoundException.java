package com.app.artisandor.exception;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(String message) {
        super(message);
    }
}
