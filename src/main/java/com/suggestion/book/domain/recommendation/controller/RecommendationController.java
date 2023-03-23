package com.suggestion.book.domain.recommendation.controller;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.domain.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping(path = "/recommendation/bestseller")
    public Mono<BestSellerListResponseDto> getBestSellerList() {
        return recommendationService.getBestSeller();
    }

    @GetMapping(path = "/recommendation/genre")
    public Mono<BestSellerListResponseDto> getByGenreBookList(@RequestParam int category) {
        return recommendationService.getByGenre(category);
    }

    @GetMapping(path = "/recommendation/popularity")
    public Mono<String> getPopularBookList(PopularBookConditionsRequestDto dto) {
        return recommendationService.getPopularBook(dto);
    }
}
