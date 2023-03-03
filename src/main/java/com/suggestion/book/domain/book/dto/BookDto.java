package com.suggestion.book.domain.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    public String title;
    public String link;
    public String image;
    public String author;
    public String discount;
    public String publisher;
    public String pubdate;
    public String isbn;
    public String description;
}
