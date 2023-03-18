package com.suggestion.book.domain.community.exception;

public class LikeNotFoundException extends RuntimeException{
    public LikeNotFoundException (String message){
        super(message);
    }
}
