package com.ll.jap.global.initData;

import com.ll.jap.domain.member.entity.Member;
import com.ll.jap.domain.member.service.MemberService;
import com.ll.jap.domain.post.comment.service.PostCommentService;
import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final MemberService memberService;
    private final PostService postService;

    @Autowired
    @Lazy // 영상봤는데 뭔말씀인지 하나도 모르겠습니다
    private BaseInitData self;

    @Bean
    public ApplicationRunner baseInitData1ApplicationRunner(PostCommentService postCommentService) {

        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1 () {
        if (memberService.count() > 0) return;

        memberService.join("System", "1234", "시스템");
        memberService.join("admin", "1234", "관리자");
        memberService.join("user1", "1234", "유저1");
        memberService.join("user2", "1234", "유저2");
        memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void work2() {
        if (postService.count() > 0) return;

        Member memberUser1 = memberService.findByUsername("user1").get();
        Member memberUser2 = memberService.findByUsername("user2").get();
        Member memberUser3 = memberService.findByUsername("user3").get();

        Post post1 = postService.write(memberUser1, "title1", "content1");
        Post post2 = postService.write(memberUser1, "title2", "content2");
        Post post3 = postService.write(memberUser2, "title3", "content3");

        post1.addComment(memberUser3,"comment1");
        post1.addComment(memberUser3,"comment2");
        post2.addComment(memberUser1,"comment3");

        post1.addTag("IT");
        post1.addTag("Spring");
        post1.addTag("JAP");
        post1.addTag("JAP");

        post2.addTag("소설");
        post2.addTag("문학");
        post2.addTag("소설가");
    }
}
