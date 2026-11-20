package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.MemberDto;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import com.back.domain.post.post.dto.PostDto;
import com.back.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {
    private final MemberService memberService;


    public record MemberJoinReqBody(
            @NotBlank
            @Size(min = 4, max = 30)
            String username,
            @NotBlank
            @Size(min = 8, max = 30)
            String password,
            @NotBlank
            @Size(min = 2, max = 30)
            String name
    ){}

    @PostMapping("/join")
    @Transactional
    public RsData<Void> join(
            @Valid @RequestBody MemberJoinReqBody body
    ){
        Member member = memberService.join(body.username, body.password, body.name);

        return new RsData<>(
                "201-1",
                "%s님 환영합니다. 회원가입이 완료되었습니다.".formatted(member.getUsername())
        );
    }
}
