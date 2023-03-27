package com.suggestion.book.domain.community.controller;

import com.suggestion.book.domain.community.dto.CommentResponseDto;
import com.suggestion.book.domain.community.dto.ContentsRequestDto;
import com.suggestion.book.domain.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(path = "/book/review/{id}/comment")
    public ResponseEntity<String> postComment(@PathVariable(name = "id") Long reviewId,
                                              @Valid @RequestBody ContentsRequestDto contentsRequestDto,
                                              @AuthenticationPrincipal User principal
                                              ) {
        commentService.createComment(reviewId, contentsRequestDto, principal.getUsername());
        return ResponseEntity.ok("댓글 작성 성공");
    }

    @GetMapping(path = "/book/review/{id}/comments")
    public Page<CommentResponseDto> getCommentListByReview(@PathVariable(name = "id") Long reviewId,
                                                           @PageableDefault(size=5, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return commentService.getCommentListByReview(reviewId, pageable);
    }

    @GetMapping(path = "/member/comments")
    public Page<CommentResponseDto> getCommentListByReview(@AuthenticationPrincipal User principal,
                                                           @PageableDefault(sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return commentService.getCommentListByMember(pageable, principal.getUsername());
    }

    @PutMapping(path = "/book/review/comment/{id}")
    public ResponseEntity<String> updateComment(@PathVariable(name = "id") Long commentId,
                                                @Valid @RequestBody ContentsRequestDto contentsRequestDto,
                                                @AuthenticationPrincipal User principal) {
        commentService.updateComment(commentId, contentsRequestDto, principal.getUsername());
        return ResponseEntity.ok("댓글 수정 성공");
    }

    @DeleteMapping(path = "/book/review/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Long commentId,
                                                @AuthenticationPrincipal User principal) {
        commentService.deleteComment(commentId, principal.getUsername());
        return ResponseEntity.ok("댓글 삭제 성공");
    }
}
