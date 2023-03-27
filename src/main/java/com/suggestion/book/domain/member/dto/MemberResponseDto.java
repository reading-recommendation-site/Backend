package com.suggestion.book.domain.member.dto;

import com.suggestion.book.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResponseDto {
    private Long no;
    private String email;
    private String name;
    private String nickname;
    private String profileImageUrl;
    public LocalDateTime createdAt;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .no(member.getNo())
                .email(member.getEmail())
                .name(member.getName())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
