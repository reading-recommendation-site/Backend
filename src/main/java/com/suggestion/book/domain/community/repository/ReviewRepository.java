package com.suggestion.book.domain.community.repository;

import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll (Pageable pageable) ;
    Page<Review> findAllByIsbn(Pageable pageable,String isbn);
    Page<Review> findAllByMember(Pageable pageable, Member member);
}
