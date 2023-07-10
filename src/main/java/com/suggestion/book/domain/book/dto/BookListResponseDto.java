package com.suggestion.book.domain.book.dto;

import com.suggestion.book.global.api.dto.NaverBookListRequestDto;
import lombok.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookListResponseDto {
    public int total;
    public int start;
    public int display;
    public List<BookResponseDto> books;

    @Builder
    public BookListResponseDto(int total, int start, int display, List<BookResponseDto> books){
        this.total = total;
        this.start = start;
        this.display = display;
        this.books = books;
    }

    public static BookListResponseDto fromNaverBookListRequestDto(NaverBookListRequestDto bookListRequestDto) {
        List<BookResponseDto> bookDtoList = bookListRequestDto.getItems().stream()
                .map(BookResponseDto::fromNaverBookRequestDto)
                .collect(toList());

        return BookListResponseDto.builder()
                .total(bookListRequestDto.getTotal())
                .start(bookListRequestDto.getStart())
                .display(bookListRequestDto.getDisplay())
                .books(bookDtoList)
                .build();
    }
}
