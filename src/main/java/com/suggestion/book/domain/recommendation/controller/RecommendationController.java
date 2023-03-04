package com.suggestion.book.domain.recommendation.controller;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping(path = "/recommendation/bestSeller")
    public Mono<BestSellerListResponseDto> getBestSellerList() {
        return recommendationService.getBestSeller();
    }
}
