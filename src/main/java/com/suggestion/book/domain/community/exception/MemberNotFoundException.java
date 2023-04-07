package com.suggestion.book.domain.community.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String massage) {
        super(massage);
    }
}
