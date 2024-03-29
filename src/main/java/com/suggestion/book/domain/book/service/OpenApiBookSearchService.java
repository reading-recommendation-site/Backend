package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.global.api.dto.AladinBookListResponseDto;
import com.suggestion.book.global.api.AladinOpenApi;
import com.suggestion.book.global.api.NaverBookOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenApiBookSearchService implements BookSearchService{

    private final NaverBookOpenApi naverBookOpenApi;
    private final AladinOpenApi aladinOpenApi;

    @Override
    public Mono<BookListResponseDto> searchByBookTitle(String title, int start) {
        return naverBookOpenApi.searchByBookTitle(title, start).map(BookListResponseDto::fromNaverBookListRequestDto);
    }

    @Override
    public Mono<AladinBookListResponseDto> searchByBookISBN(String isbn) {
        return aladinOpenApi.searchByBookISBN(isbn);
    }
}
