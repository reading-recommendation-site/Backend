package com.suggestion.book.global.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverBookRequestDto {
    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;
}
