package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.dto.LikeResponseDto;
import com.suggestion.book.domain.community.dto.ReviewResponseDto;
import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.community.exception.LikeNotFoundException;
import com.suggestion.book.domain.community.exception.MemberIdMismatchException;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public LikeResponseDto getLike(Long reviewId, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId);
        Optional<Like> likeOpt = likeRepository.findByMemberAndReview(member,review);
        List<Like> likeList = likeRepository.findByReview(review);
        return LikeResponseDto.builder()
                .likeNo(likeOpt.map(Like::getNo).orElse(0L))
                .count(likeList.size())
                .build();
    }

    public Page<ReviewResponseDto> getLikeByMember(Pageable pageable, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return likeRepository.findAllByMember(pageable, member).map(ReviewResponseDto::fromByLike);
    }

    @Transactional
    public void addLike(Long reviewId, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Member member = memberRepository.findByMemberId(memberId);
        if(likeRepository.findByMemberAndReview(member,review).isEmpty()){
            likeRepository.save(Like.builder()
                            .review(review)
                            .member(member)
                            .build());
        }
    }

    @Transactional
    public void deleteLike(Long reviewId, String memberId,Long likeId) {
        reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        Like like = likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("좋아요가 없습니다."));
        if (!memberId.equals(like.getMember().getMemberId())) {
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        likeRepository.deleteById(likeId);
    }
}
