package com.suggestion.book.domain.community.controller;

import com.suggestion.book.domain.community.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping(path = "review/{id}/like")
    public ResponseEntity<Long> getLikeId(@PathVariable(name = "id") Long reviewId, @AuthenticationPrincipal User principal){
        return ResponseEntity.ok(likeService.getLike(reviewId,principal.getUsername()));
    }

    @PostMapping(path = "review/{id}/like")
    public ResponseEntity<String> addLike(@PathVariable(name = "id") Long reviewId, @AuthenticationPrincipal User principal){
        likeService.addLike(reviewId,principal.getUsername());
        return ResponseEntity.ok("좋아요 클릭");
    }

    @DeleteMapping(path = "review/{id}/like/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable(name = "id") Long reviewId, @PathVariable(name = "likeId") Long likeId,
                                             @AuthenticationPrincipal User principal){
        likeService.deleteLike(reviewId,principal.getUsername(),likeId);
        return ResponseEntity.ok("좋아요 취소");
    }
}
