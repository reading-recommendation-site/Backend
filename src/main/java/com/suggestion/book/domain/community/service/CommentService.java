package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.dto.CommentResponseDto;
import com.suggestion.book.domain.community.dto.ContentsRequestDto;
import com.suggestion.book.domain.community.entity.Comment;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.community.exception.CommentNotFoundException;
import com.suggestion.book.domain.community.exception.MemberIdMismatchException;
import com.suggestion.book.domain.community.exception.MemberNotFoundException;
import com.suggestion.book.domain.community.exception.ReviewNotFoundException;
import com.suggestion.book.domain.community.repository.CommentRepository;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createComment(Long reviewId, ContentsRequestDto contentsRequestDto, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        commentRepository.save(Comment.builder()
                .review(review)
                .member(member)
                .contents(contentsRequestDto.contents)
                .build()
        );
    }

    public Page<CommentResponseDto> getCommentListByReview(Long reviewId, Pageable pageable) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        return commentRepository.findAllByReview(pageable, review).map(CommentResponseDto::from);
    }

    public Page<CommentResponseDto> getCommentListByMember(Pageable pageable, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        return commentRepository.findAllByMember(pageable, member).map(CommentResponseDto::from);
    }

    @Transactional
    public void updateComment(Long commentId, ContentsRequestDto contentsRequestDto, String memberId) {
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        Comment comment = commentOpt.orElseThrow(() -> new CommentNotFoundException("댓글이 존재 하지 않습니다."));
        if (!memberId.equals(comment.getMember().getMemberId())) {
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        comment.updateContents(contentsRequestDto);
    }

    @Transactional
    public void deleteComment(Long commentId, String memberId) {
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        Comment comment = commentOpt.orElseThrow(() -> new CommentNotFoundException("댓글이 존재 하지 않습니다."));
        if (!memberId.equals(comment.getMember().getMemberId())) {
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        commentRepository.delete(comment);
    }
}
