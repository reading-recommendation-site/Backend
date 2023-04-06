package com.suggestion.book.domain.community.repository;

import com.suggestion.book.domain.community.entity.Like;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Modifying
    @Query("delete from Like l where l.review.no =:no ")
    void deleteByReview(Long no);

    Optional<Like> findByMemberAndReview(Member member, Review review);
    List<Like> findByReview(Review review);
    @EntityGraph(attributePaths = {"review.member","review"})
    Page<Like> findAllByMember(Pageable pageable, Member member);
}
