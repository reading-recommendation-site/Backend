package com.suggestion.book.domain.community.exception;

public class MemberIdMismatchException extends  RuntimeException{
    public MemberIdMismatchException(String message){
        super(message);
    }
}
