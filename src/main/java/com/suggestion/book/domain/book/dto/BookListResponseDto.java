package com.suggestion.book.domain.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookListResponseDto {
    public int total;
    public int start;
    public int display;
    public List<BookDto> items;
}
