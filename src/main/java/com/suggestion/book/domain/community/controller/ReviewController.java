package com.suggestion.book.domain.community.controller;

import com.suggestion.book.domain.community.dto.ContentsRequestDto;
import com.suggestion.book.domain.community.dto.ReviewRequestDto;
import com.suggestion.book.domain.community.dto.ReviewResponseDto;
import com.suggestion.book.domain.community.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(path = "/book/review")
    public ResponseEntity<String> postReview(@Valid @RequestBody ReviewRequestDto reviewRequestDto,
                                             @AuthenticationPrincipal User principal) {
        reviewService.createReview(reviewRequestDto, principal.getUsername());
        return ResponseEntity.ok("리뷰 작성 완료");
    }

    @GetMapping(path = "/reviews")
    public Page<ReviewResponseDto> getReviewList(@RequestParam(defaultValue = "0")  final int pageNumber,
                                                 @RequestParam(defaultValue = "5")  final int size) {
        return reviewService.getReviewList(
                PageRequest.of(pageNumber, size, Sort.by("createdAt").descending())).map(ReviewResponseDto::from);
    }

    @PutMapping(path = "/book/review/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") Long reviewId,
                                               @Valid @RequestBody ContentsRequestDto contentsRequestDto,
                                               @AuthenticationPrincipal User principal) {
        reviewService.updateReview(reviewId,contentsRequestDto, principal.getUsername());
        return ResponseEntity.ok("리뷰 수정 완료");
    }

    @DeleteMapping(path = "/book/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long reviewId,
                                               @AuthenticationPrincipal User principal) {
        reviewService.deleteReview(reviewId, principal.getUsername());
        return ResponseEntity.ok("리뷰 삭제 완료");
    }
}
