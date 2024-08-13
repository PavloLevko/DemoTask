package com.example.demotask.exception;

public class MemberCannotBeDeletedException extends RuntimeException {
    public MemberCannotBeDeletedException(String message) {
        super(message);
    }
}