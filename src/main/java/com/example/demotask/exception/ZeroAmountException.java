package com.example.demotask.exception;

public class ZeroAmountException extends RuntimeException {
    public ZeroAmountException(String message) {
        super(message);
    }
}