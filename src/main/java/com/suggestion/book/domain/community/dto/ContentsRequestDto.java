package com.suggestion.book.domain.community.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ContentsRequestDto {
    @NotEmpty(message = "contents 가 비워있습니다.")
    public String contents;
}
