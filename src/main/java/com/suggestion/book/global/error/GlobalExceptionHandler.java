package com.suggestion.book.global.error;

import com.suggestion.book.domain.community.exception.CommentNotFoundException;
import com.suggestion.book.domain.community.exception.InvalidISBNException;
import com.suggestion.book.domain.community.exception.MemberIdMismatchException;
import com.suggestion.book.domain.community.exception.ReviewNotFoundException;
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
     * 유효성 체크에 통과 하지 못하면  MethodArgumentNotValidException 이 발생 한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException : "+ e.getBindingResult());
        String errorLog = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(errorLog, HttpStatus.BAD_REQUEST);
    }

    /**
     * 리뷰 작성시 isbn 이 존재 하지 않는 경우 발생 한다.
     */
    @ExceptionHandler(InvalidISBNException.class)
    protected ResponseEntity<?> handleInvalidISBNException(InvalidISBNException e) {
        log.warn("InvalidISBNException : "+ e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * db에 찾고자 하는 리뷰가 존재 하지 않는 경우 발생 한다.
     */
    @ExceptionHandler(ReviewNotFoundException.class)
    protected ResponseEntity<?> handleReviewNotFoundException(ReviewNotFoundException e) {
        log.warn("ReviewNotFoundException : "+ e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 작성한 멤버와 수정 할려는 멤버가 다른 경우 발생 한다.
     */
    @ExceptionHandler(MemberIdMismatchException.class)
    protected ResponseEntity<?> handleMemberIdMismatchException(MemberIdMismatchException e) {
        log.warn("MemberIdMismatchException : "+ e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * db에 찾고자 하는 댓글이 존재 하지 않는 경우 발생 한다.
     */
    @ExceptionHandler(CommentNotFoundException.class)
    protected ResponseEntity<?> handleCommentNotFoundException(CommentNotFoundException e) {
        log.warn("CommentNotFoundException : "+e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
