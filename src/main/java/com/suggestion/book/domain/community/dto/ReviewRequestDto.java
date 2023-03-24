package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ReviewRequestDto {
    @Size(min = 13, max = 13, message = "isbn 은 13개의 숫자로 이루어져야만 합니다.")
    public String isbn;

    @NotEmpty(message = "contents 가 비워있습니다.")
    public String contents;

    @Max(value = 10, message = "10 이하만 가능합니다.")
    @Min(value = 1, message = "1 이상만 가능합니다.")
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
