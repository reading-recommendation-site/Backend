package com.suggestion.book.domain.book.repository;

import com.suggestion.book.domain.book.entity.Bookmark;
import com.suggestion.book.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findAllByMember(Pageable pageable, Member member);

    Optional<Bookmark> findByMemberAndIsbn(Member member, String isbn);
    List<Bookmark> findAllByIsbn(String isbn);
}
