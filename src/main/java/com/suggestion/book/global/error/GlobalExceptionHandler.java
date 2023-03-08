package com.suggestion.book.global.error;

import com.suggestion.book.domain.community.exception.InvalidISBNException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("handleMethodArgumentNotValidException : "+ e.getBindingResult());
        String errorLog = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(errorLog, HttpStatus.BAD_REQUEST);
    }

    /**
     * 리뷰 작성시 isbn 이 존재하지 않는 경우 발생한다.
     */
    @ExceptionHandler(InvalidISBNException.class)
    protected ResponseEntity<?> handleInvalidISBNException(InvalidISBNException e) {
        log.warn("handleMethodArgumentNotValidException : "+ e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
