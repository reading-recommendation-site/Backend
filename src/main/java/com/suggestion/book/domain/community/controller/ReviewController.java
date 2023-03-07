package com.suggestion.book.domain.community.controller;

import com.suggestion.book.domain.community.dto.ReviewRequestDto;
import com.suggestion.book.domain.community.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(path = "/book/review")
    public ResponseEntity<String> postReview(@RequestBody ReviewRequestDto reviewRequestDto,
                                             @AuthenticationPrincipal User principal) {
        reviewService.createReview(reviewRequestDto, principal.getUsername());
        return ResponseEntity.ok("리뷰 작성 완료");
    }
}
