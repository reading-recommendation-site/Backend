package com.suggestion.book.global.api;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NaverBookOpenApi {
    private final WebClient naverWebClientApi;

    public static final String BOOK_TITLE_URI = "/search/book_adv.json";

    public Mono<BookListResponseDto> searchByBookTitle(String title, int start) {
        return naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_TITLE_URI)
                        .queryParam("d_titl", title)
                        .queryParam("start", start)
                        .build())
                .retrieve()
                .bodyToMono(BookListResponseDto.class);
    }
}
