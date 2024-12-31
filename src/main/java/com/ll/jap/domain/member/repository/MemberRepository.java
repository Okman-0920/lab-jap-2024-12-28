package com.ll.jap.domain.member.repository;

import com.ll.jap.domain.member.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
