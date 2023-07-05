package com.suggestion.book.global.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NaverBookListRequestDto {
    private int total;
    private int start;
    private int display;
    private List<NaverBookRequestDto> items;
}
