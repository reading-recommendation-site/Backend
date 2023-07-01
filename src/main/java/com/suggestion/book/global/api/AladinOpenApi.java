package com.suggestion.book.global.api;

import com.suggestion.book.domain.recommendation.dto.DetailBookResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AladinOpenApi {
    private final WebClient aladinWebClientApi;
    private final ApiProperties apiProperties;

    public static final String BOOK_ISBN_URI = "/ItemLookUp.aspx";

    public Mono<DetailBookResponseDto> searchByBookISBN(String isbn) {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_ISBN_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("ItemId", isbn)
                        .queryParam("ItemIdType","ISBN13")
                        .queryParam("Version",20131101)
                        .queryParam("Cover","Big")
                        .queryParam("OptResult","ratingInfo,cardReviewImgList")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(DetailBookResponseDto.class);
    }
}
