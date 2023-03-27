package com.suggestion.book.domain.community.repository;

import com.suggestion.book.domain.community.entity.Comment;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("delete from Comment c where c.review.no =:no ")
    void deleteByReview(Long no);

    Page<Comment> findAllByReview(Pageable pageable, Review review);
    Page<Comment> findAllByMember(Pageable pageable, Member member);
}
