package com.suggestion.book.domain.recommendation.exception;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(String message) {
        super(message);
    }
}
