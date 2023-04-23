package com.suggestion.book.domain.book.dto;

import com.suggestion.book.domain.book.entity.Bookmark;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkResponseDto {
    private Long no;
    private String bookTitle;
    private String bookAuthor;
    private String bookImgUrl;
    private String isbn;

    public static BookmarkResponseDto from(Bookmark bookmark) {
        return BookmarkResponseDto.builder()
                .no(bookmark.getNo())
                .bookAuthor(bookmark.getBookAuthor())
                .bookTitle(bookmark.getBookTitle())
                .bookImgUrl(bookmark.getBookImgUrl())
                .isbn(bookmark.getIsbn())
                .build();
    }
}
