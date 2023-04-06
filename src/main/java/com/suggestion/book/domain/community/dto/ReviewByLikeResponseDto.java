package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Like;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewByLikeResponseDto {
    public Long reviewNo;
    public String memberNickname;
    public String bookTitle;
    public String bookAuthor;
    public String bookImgUrl;
    public int grade;
    public String contents;
    public LocalDateTime createdAt;

    public static ReviewByLikeResponseDto from(Like like) {
        return ReviewByLikeResponseDto.builder()
                .reviewNo(like.getReview().getNo())
                .memberNickname(like.getReview().getMember().getName())
                .bookTitle(like.getReview().getBookTitle())
                .bookAuthor(like.getReview().getBookAuthor())
                .bookImgUrl(like.getReview().getBookImgUrl())
                .grade(like.getReview().getGrade())
                .contents(like.getReview().getContents())
                .createdAt(like.getCreatedAt())
                .build();
    }
}
