package com.suggestion.book.domain.book.controller;

import com.suggestion.book.domain.book.dto.SimilarBookListResponseDto;
import com.suggestion.book.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping(path = "/search/book/{isbn}/similar-book/{type}")
    public Mono<SimilarBookListResponseDto> getSimilarBookList(@PathVariable() String isbn, @PathVariable() String type){
        return bookService.getSimilarBookList(isbn, type);
    }
}
