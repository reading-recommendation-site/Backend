package com.suggestion.book.domain.book.dto;

import com.suggestion.book.domain.book.entity.Bookmark;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookmarkCountResponseDto {
    public int count;
    public Long bookmarkNo;

    public static BookmarkCountResponseDto from(List<Bookmark> bookmark){
        return BookmarkCountResponseDto.builder()
                .count(bookmark.size())
                .bookmarkNo(0L)
                .build();
    }
    public static BookmarkCountResponseDto from(List<Bookmark> bookmark, List<Bookmark> bookmarkNo){
        return BookmarkCountResponseDto.builder()
                .count(bookmark.size())
                .bookmarkNo(bookmarkNo.size() == 1 ? bookmarkNo.get(0).getNo() : 0)
                .build();
    }
}
