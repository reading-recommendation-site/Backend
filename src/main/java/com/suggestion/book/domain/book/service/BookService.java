package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.SimilarBookListResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {
    private final WebClient data4libraryWebClientApi;
    private final ApiProperties apiProperties;
    public static final String URI = "/recommandList";

    public Mono<SimilarBookListResponseDto> getSimilarBookList(String isbn, String type) {
        return data4libraryWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                        .queryParam("isbn13", isbn)
                        .queryParam("type", type)
                        .queryParam("format", "json")
                        .build())
                .retrieve()
                .bodyToMono(SimilarBookListResponseDto.class);
    }
}
