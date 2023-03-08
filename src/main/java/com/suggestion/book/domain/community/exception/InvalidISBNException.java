package com.suggestion.book.domain.community.exception;

public class InvalidISBNException extends RuntimeException {
    public InvalidISBNException(String message) {
        super(message);
    }
}
