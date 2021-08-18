package com.ssafy.kiwi.model.authentication.dto;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String identity;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getIdentity());
    }
}