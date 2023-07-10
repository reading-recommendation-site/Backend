package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.domain.recommendation.dto.DetailBookResponseDto;
import reactor.core.publisher.Mono;

public interface BookSearchService {

    Mono<BookListResponseDto> searchByBookTitle(String title, int start);

    Mono<DetailBookResponseDto> searchByBookISBN(String isbn);
}
