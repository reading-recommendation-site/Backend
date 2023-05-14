package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import com.suggestion.book.domain.recommendation.exception.KeyNotFoundException;
import com.suggestion.book.domain.recommendation.repository.PopularBookRedisRepository;
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
    private final WebClient data4libraryWebClientApi;
    private final WebClient aladinWebClientApi;
    private final ApiProperties apiProperties;
    private final PopularBookRedisRepository popularBookRedisRepository;
    //private final BestsellerRedisRepository bestsellerRedisRepository;

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

//    public BestSellerListResponseDto getBestSeller(String division) {
//        return bestsellerRedisRepository.findById(division)
//                .orElseThrow(() -> new KeyNotFoundException(division+" 가 존재하지 않습니다.")).getBestSellerListResponseDto();
//    }

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

    public PopularBookListResponseDto getPopularBook(PopularBookConditionsRequestDto conditionsDto) {
        MultiValueMap<String, String> params = MultiValueMapConverterUtil.convert(conditionsDto);
        Mono<PopularBookListResponseDto> popularBookListResponseDtoMono = data4libraryWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(POPULAR_BOOK_URI)
                        .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                        .queryParams(params)
                        .queryParam("format","json")
                        .build())
                .retrieve()
                .bodyToMono(PopularBookListResponseDto.class);
        return popularBookListResponseDtoMono.block();
    }

    public PopularBookListResponseDto getPopularBook(String division) {
        return popularBookRedisRepository.findById(division)
                .orElseThrow(() -> new KeyNotFoundException(division+" 가 존재하지 않습니다.")).getPopularBookListResponseDto();
    }
}
