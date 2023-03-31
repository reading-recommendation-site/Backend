package com.suggestion.book.domain.community.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookISBNResponseDto {
    public int total;
    public List<BookItem> items;

    public static class BookItem{
        public String title;
        public String image;
        public String author;
    }
}
