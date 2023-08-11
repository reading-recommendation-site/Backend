package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.global.api.dto.AladinBookListResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OpenApiBookSearchServiceTest {
    @Autowired
    private OpenApiBookSearchService openApiBookSearchService;

    @Test
    @DisplayName("네이버 API를 통한 책 제목 조회")
    void searchByBookTitle(){
        // given
        String bookTitle = "무지개 물고기";
        int start = 1;

        // when
        BookListResponseDto responseDto = openApiBookSearchService.searchByBookTitle(bookTitle, start).block();

        //then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getBooks().get(0).getTitle()).isEqualTo(bookTitle);
    }

    @Test
    @DisplayName("알라딘 API를 통한 ISBN 조회")
    void searchByISBN(){
        // given
        String isbn = "9788972590637"; // 무지개 물고기 isbn

        // when
        AladinBookListResponseDto responseDto = openApiBookSearchService.searchByBookISBN(isbn).block();

        //then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getItem().get(0).isbn13).isEqualTo(isbn);
    }

}