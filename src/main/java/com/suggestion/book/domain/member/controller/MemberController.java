package com.suggestion.book.domain.member.controller;

import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.service.MemberService;
import com.suggestion.book.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(path = "/member")
    public ApiResponse<?> getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.getMember(principal.getUsername());

        return ApiResponse.success("member", member);
    }
}
