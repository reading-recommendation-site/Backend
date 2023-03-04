package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final WebClient aladinWebClientApi;
    private final ApiProperties apiProperties;
    private static final String URI = "/ItemList.aspx";

    public Mono<BestSellerListResponseDto> getBestSeller() {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("SearchTarget","Book")
                        .queryParam("Version","20131101")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(BestSellerListResponseDto.class);
    }
}
