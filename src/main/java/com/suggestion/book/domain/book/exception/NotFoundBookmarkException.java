package com.suggestion.book.domain.book.exception;

public class NotFoundBookmarkException extends RuntimeException{
    public NotFoundBookmarkException(String message) {
        super(message);
    }
}
