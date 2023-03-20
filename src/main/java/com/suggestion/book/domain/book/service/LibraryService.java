package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.LibraryListResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final WebClient data4libraryWebClientApi;
    private final ApiProperties apiProperties;
    public static final String URI = "/libSrchByBook";

    public Mono<LibraryListResponseDto> libraryWithBooks(String isbn, int region) {
        return data4libraryWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                        .queryParam("isbn", isbn)
                        .queryParam("region", region)
                        .queryParam("pageSize", 20)
                        .queryParam("format", "json")
                        .build())
                .retrieve()
                .bodyToMono(LibraryListResponseDto.class);
    }
}
