package com.ll.jap.global.initData;

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

        Post post1 = postService.write("title1", "content1");
    }
}
