package com.example.demotask.exception;

public class BookCannotBeDeletedException extends RuntimeException {
    public BookCannotBeDeletedException(String message) {
        super(message);
    }
}
