package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDto {
    public Long no;
    public String memberName;
    public int grade;
    public String contents;
    public LocalDateTime createdAt;

    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .no(review.getNo())
                .memberName(review.getMember().getName())
                .grade(review.getGrade())
                .contents(review.getContents())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDto fromByLike(Like like) {
        return ReviewResponseDto.builder()
                .no(like.getReview().getNo())
                .memberName(like.getReview().getMember().getName())
                .grade(like.getReview().getGrade())
                .contents(like.getReview().getContents())
                .createdAt(like.getCreatedAt())
                .build();
    }
}
