package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import com.suggestion.book.global.utils.MultiValueMapConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final WebClient aladinWebClientApi;
    private final WebClient data4libraryWebClientApi;
    private final ApiProperties apiProperties;
    private static final String ALADIN_URI = "/ItemList.aspx";
    private static final String POPULAR_BOOK_URI = "/loanItemSrch";

    public Mono<BestSellerListResponseDto> getBestSeller() {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(ALADIN_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("SearchTarget","Book")
                        .queryParam("Version",20131101)
                        .queryParam("Cover","Big")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(BestSellerListResponseDto.class);
    }

    public Mono<BestSellerListResponseDto> getByGenre(int category) {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(ALADIN_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("SearchTarget","Book")
                        .queryParam("Version",20131101)
                        .queryParam("CategoryId",category)
                        .queryParam("Cover","Big")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(BestSellerListResponseDto.class);
    }

    public Mono<String> getPopularBook(PopularBookConditionsRequestDto conditionsDto) {
        MultiValueMap<String, String> params = MultiValueMapConverterUtil.convert(conditionsDto);
        System.out.println(params);
        return data4libraryWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(POPULAR_BOOK_URI)
                        .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                        .queryParams(params)
                        .queryParam("format","json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
