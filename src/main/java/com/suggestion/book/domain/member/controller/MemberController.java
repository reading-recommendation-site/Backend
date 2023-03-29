package com.suggestion.book.domain.member.controller;

import com.suggestion.book.domain.member.dto.MemberResponseDto;
import com.suggestion.book.domain.member.dto.NicknameRequestDto;
import com.suggestion.book.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(path = "/member")
    public MemberResponseDto getMember(@AuthenticationPrincipal User principal) {
        return memberService.getMember(principal.getUsername());
    }

    @PostMapping(path = "/member/nickname")
    public ResponseEntity<String> updateNickname(@AuthenticationPrincipal User principal,
                                              @RequestBody NicknameRequestDto nicknameRequestDto) {
        memberService.updateNickname(nicknameRequestDto, principal.getUsername());
        return ResponseEntity.ok("닉네임 변경 성공");
    }
}
