package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.community.exception.ReviewNotFoundException;
import com.suggestion.book.domain.community.repository.LikeRepository;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public void addLike(Long reviewId, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId);
        if(likeRepository.findByMemberAndReview(member,review).isEmpty()){
            likeRepository.save(Like.builder()
                    .review(review)
                    .member(member).build());
        }
    }
}
