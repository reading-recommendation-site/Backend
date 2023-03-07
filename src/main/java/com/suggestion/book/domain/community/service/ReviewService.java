package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.dto.ReviewRequestDto;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public void createReview(ReviewRequestDto reviewRequestDto, String memberName) {
        Member member = memberRepository.findByMemberId(memberName);
        reviewRepository.save(reviewRequestDto.toEntity(member));
    }
}
