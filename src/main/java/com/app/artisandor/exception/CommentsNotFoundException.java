package com.app.artisandor.exception;

public class CommentsNotFoundException extends RuntimeException {
    public CommentsNotFoundException(String message) {
        super(message);
    }
}
