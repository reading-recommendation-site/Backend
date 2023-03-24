package com.suggestion.book.domain.book.controller;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.domain.book.service.BookSearchService;
import com.suggestion.book.domain.recommendation.dto.DetailBookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class BookSearchController {
    private final BookSearchService bookSearchService;

    @GetMapping(path = "/search/book")
    public Mono<BookListResponseDto> getBookList(@RequestParam String title, @RequestParam int start){
        return bookSearchService.searchByBookTitle(title,start);
    }

    @GetMapping(path = "/search/book/{isbn}")
    public Mono<DetailBookResponseDto> getBook(@PathVariable() String isbn) {
        return bookSearchService.searchByBookISBN(isbn);
    }
}
