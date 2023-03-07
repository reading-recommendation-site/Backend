package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    public String isbn;
    public String contents;
    public int grade;

    public Review toEntity(Member member) {
        return Review.builder()
                .member(member)
                .isbn(isbn)
                .contents(contents)
                .grade(grade)
                .build();
    }
}
