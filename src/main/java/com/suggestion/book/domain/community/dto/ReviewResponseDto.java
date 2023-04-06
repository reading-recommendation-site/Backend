package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDto {
    public Long no;
    public String memberNickname;
    public String bookTitle;
    public String bookAuthor;
    public String bookImgUrl;
    public int grade;
    public String contents;
    public int commentCount;
    public int likeCount;
    public boolean liked;
    public LocalDateTime createdAt;

    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .no(review.getNo())
                .memberNickname(review.getMember().getNickname())
                .bookTitle(review.getBookTitle())
                .bookAuthor(review.getBookAuthor())
                .bookImgUrl(review.getBookImgUrl())
                .grade(review.getGrade())
                .contents(review.getContents())
                .commentCount(review.getComments().size())
                .likeCount(review.getLikes().size())
                .liked(false)
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDto from(Review review, String memberId) {
        return ReviewResponseDto.builder()
                .no(review.getNo())
                .memberNickname(review.getMember().getNickname())
                .bookTitle(review.getBookTitle())
                .bookAuthor(review.getBookAuthor())
                .bookImgUrl(review.getBookImgUrl())
                .grade(review.getGrade())
                .contents(review.getContents())
                .commentCount(review.getComments().size())
                .likeCount(review.getLikes().size())
                .liked(review.getLikes().stream().anyMatch(like -> like.getMember().getMemberId().equals(memberId)))
                .createdAt(review.getCreatedAt())
                .build();
    }
}
