package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookSearchService {

    private final WebClient naverWebClientApi;

    public static final String URI = "/search/book_adv.json";

    public Mono<BookListResponseDto> searchByBookTitle(String title, int start) {
        return naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("d_titl", title)
                        .queryParam("start", start)
                        .build())
                .retrieve()
                .bodyToMono(BookListResponseDto.class);
    }
}
