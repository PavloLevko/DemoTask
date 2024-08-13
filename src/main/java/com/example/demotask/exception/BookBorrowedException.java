package com.example.demotask.exception;

public class BookBorrowedException extends RuntimeException{
    public BookBorrowedException(String message) {
        super(message);
    }
}