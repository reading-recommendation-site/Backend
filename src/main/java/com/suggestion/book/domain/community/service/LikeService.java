package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.dto.ReviewByLikeResponseDto;
import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.community.exception.LikeNotFoundException;
import com.suggestion.book.domain.community.exception.MemberNotFoundException;
import com.suggestion.book.domain.community.exception.ReviewNotFoundException;
import com.suggestion.book.domain.community.repository.LikeRepository;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public Page<ReviewByLikeResponseDto> getLikeByMember(Pageable pageable, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        return likeRepository.findAllByMember(pageable, member).map(ReviewByLikeResponseDto::from);
    }

    @Transactional
    public void addLike(Long reviewId, String memberId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        if(likeRepository.findByMemberAndReview(member,review).isEmpty()){
            likeRepository.save(Like.builder()
                            .review(review)
                            .member(member)
                            .build());
        }
    }

    @Transactional
    public void deleteLike(Long reviewId, String memberId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        Like like = likeRepository.findByMemberAndReview(member, review)
                .orElseThrow(() -> new LikeNotFoundException("좋아요가 없습니다."));
        likeRepository.delete(like);
    }
}
