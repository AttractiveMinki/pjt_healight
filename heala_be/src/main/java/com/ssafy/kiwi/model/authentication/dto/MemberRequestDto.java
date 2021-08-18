package com.ssafy.kiwi.model.authentication.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.kiwi.model.domain.authentication.entity.Authority;
import com.ssafy.kiwi.model.domain.authentication.entity.Member;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String identity;
    private String password;
    private String email;
    private String name;
    private String image;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .identity(identity)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .image(image)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(identity, password);
    }
}