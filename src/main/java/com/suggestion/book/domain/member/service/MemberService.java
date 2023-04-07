package com.suggestion.book.domain.member.service;

import com.suggestion.book.domain.community.exception.MemberNotFoundException;
import com.suggestion.book.domain.member.dto.MemberResponseDto;
import com.suggestion.book.domain.member.dto.NicknameRequestDto;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto getMember(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        return MemberResponseDto.from(member);
    }

    @Transactional
    public void updateNickname(NicknameRequestDto nicknameRequestDto, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        member.updateNickname(nicknameRequestDto);
    }
}
