package com.suggestion.book.global.api;

import com.suggestion.book.global.api.dto.AladinBestSellerResponseDto;
import com.suggestion.book.global.api.dto.AladinBookListResponseDto;
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
    private static final String BOOK_BESTSELLER_URI = "/ItemList.aspx";

    public Mono<AladinBookListResponseDto> searchByBookISBN(String isbn) {
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
                .bodyToMono(AladinBookListResponseDto.class);
    }

    public Mono<AladinBestSellerResponseDto> getAllBestSeller() {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_BESTSELLER_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("SearchTarget","Book")
                        .queryParam("Version",20131101)
                        .queryParam("Cover","Big")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(AladinBestSellerResponseDto.class);
    }

    public Mono<AladinBestSellerResponseDto> getBestSellerByGenre(int category) {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_BESTSELLER_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("SearchTarget","Book")
                        .queryParam("Version",20131101)
                        .queryParam("CategoryId",category)
                        .queryParam("Cover","Big")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(AladinBestSellerResponseDto.class);
    }
}
