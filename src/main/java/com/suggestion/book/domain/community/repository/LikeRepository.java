package com.suggestion.book.domain.community.repository;

import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndReview(Member member, Review review);
}
