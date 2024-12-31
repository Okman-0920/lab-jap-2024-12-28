package com.ll.jap.domain.member.service;

import com.ll.jap.domain.member.member.Member;
import com.ll.jap.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join (String userId, String password, String nickname) {
        Member post = Member
                .builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .build();

        return memberRepository.save(post);
    };

    public long count() {
        return memberRepository.count();
    }
}
