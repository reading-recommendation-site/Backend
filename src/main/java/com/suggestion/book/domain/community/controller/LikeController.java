package com.suggestion.book.domain.community.controller;

import com.suggestion.book.domain.community.dto.LikeResponseDto;
import com.suggestion.book.domain.community.dto.ReviewResponseDto;
import com.suggestion.book.domain.community.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping(path = "review/{id}/like")
    public ResponseEntity<LikeResponseDto> getLikeId(@PathVariable(name = "id") Long reviewId, @AuthenticationPrincipal User principal){
        return ResponseEntity.ok(likeService.getLike(reviewId,principal.getUsername()));
    }

    @GetMapping(path = "member/likes")
    public Page<ReviewResponseDto> getLikeByMember(
            @PageableDefault(size=20, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User principal) {
        return likeService.getLikeByMember(pageable, principal.getUsername());
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
