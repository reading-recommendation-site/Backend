package com.suggestion.book.domain.member.controller;

import com.suggestion.book.domain.member.dto.MemberResponseDto;
import com.suggestion.book.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(path = "/member")
    public MemberResponseDto getUser(@AuthenticationPrincipal User principal) {
        return memberService.getMember(principal.getUsername());
    }
}
