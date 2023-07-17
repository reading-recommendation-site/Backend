package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.global.api.dto.AladinBookListResponseDto;
import reactor.core.publisher.Mono;

public interface BookSearchService {

    Mono<BookListResponseDto> searchByBookTitle(String title, int start);

    Mono<AladinBookListResponseDto> searchByBookISBN(String isbn);
}
