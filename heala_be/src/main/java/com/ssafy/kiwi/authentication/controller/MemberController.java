package com.ssafy.kiwi.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.authentication.dto.MemberResponseDto;
import com.ssafy.kiwi.model.authentication.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{identity}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String identity) {
        return ResponseEntity.ok(memberService.getMemberInfo(identity));
    }
}