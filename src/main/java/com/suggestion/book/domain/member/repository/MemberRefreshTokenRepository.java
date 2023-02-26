package com.suggestion.book.domain.member.repository;

import com.suggestion.book.domain.member.entity.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {
    MemberRefreshToken findByMemberId(String memberId);
    MemberRefreshToken findByMemberIdAndRefreshToken(String memberId, String refreshToken);
}
