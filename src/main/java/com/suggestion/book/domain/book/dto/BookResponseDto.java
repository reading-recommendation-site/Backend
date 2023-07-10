package com.suggestion.book.domain.book.dto;

import com.suggestion.book.global.api.dto.NaverBookRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponseDto {
    private String title;
    private String link;
    private String image;
    private String author;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;

    @Builder
    public BookResponseDto(String title, String link, String image, String author, String publisher,
                           String pubdate, String isbn, String description) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.isbn = isbn;
        this.description = description;
    }

    public static BookResponseDto fromNaverBookRequestDto(NaverBookRequestDto bookRequestDto) {
        return BookResponseDto.builder()
                .title(bookRequestDto.getTitle())
                .link(bookRequestDto.getLink())
                .image(bookRequestDto.getImage())
                .author(bookRequestDto.getAuthor())
                .publisher(bookRequestDto.getPublisher())
                .pubdate(bookRequestDto.getPubdate())
                .isbn(bookRequestDto.getIsbn())
                .description(bookRequestDto.getDescription())
                .build();
    }
}
