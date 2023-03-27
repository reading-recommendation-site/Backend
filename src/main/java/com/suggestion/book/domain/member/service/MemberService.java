package com.suggestion.book.domain.member.service;

import com.suggestion.book.domain.member.dto.MemberResponseDto;
import com.suggestion.book.domain.member.dto.NicknameRequestDto;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto getMember(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return MemberResponseDto.from(member);
    }

    public void updateNickname(NicknameRequestDto nicknameRequestDto, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        member.updateNickname(nicknameRequestDto);
    }
}
