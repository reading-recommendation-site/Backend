package com.suggestion.book.domain.book.controller;

import com.suggestion.book.domain.book.dto.LibraryListResponseDto;
import com.suggestion.book.domain.book.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping(path = "/search/book/{isbn}/library/region/{region}")
    public Mono<LibraryListResponseDto> libraryWithBooks(@PathVariable(name = "isbn") String isbn,
                                                         @PathVariable(name = "region") int region){
        return libraryService.libraryWithBooks(isbn,region);
    }
}
