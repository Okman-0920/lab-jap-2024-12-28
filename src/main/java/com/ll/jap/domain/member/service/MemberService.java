package com.ll.jap.domain.member.service;

import com.ll.jap.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository MemberRepository;
}
