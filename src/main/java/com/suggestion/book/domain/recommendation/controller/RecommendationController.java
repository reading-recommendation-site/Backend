package com.suggestion.book.domain.recommendation.controller;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import com.suggestion.book.domain.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/recommendation/all-bestseller")
    public BestSellerListResponseDto getAllBestSellerList() {
        return recommendationService.getBestSeller("ALL");
    }

    @GetMapping(path = "/recommendation/genre")
    public Mono<BestSellerListResponseDto> getByGenreBookList(@RequestParam int category) {
        return recommendationService.getByGenre(category);
    }

    @GetMapping(path = "/recommendation/popularity")
    public PopularBookListResponseDto getPopularBookList(PopularBookConditionsRequestDto dto) {
        return recommendationService.getPopularBook(dto);
    }

    @GetMapping(path = "/recommendation/popularity/{division}")
    public PopularBookListResponseDto getPopularBookList(@PathVariable String division) {
        return recommendationService.getPopularBook(division.toUpperCase());
    }
}
