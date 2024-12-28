package com.ll.jap.global.initData;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {

        return args -> {
            Post post1 = postService.write("제목1", "내용1");
            System.out.println(post1.getId() + "번 글이 생성되었습니다.");

            Post post2 = postService.write("제목2", "내용2");
            System.out.println(post2.getId() + "번 글이 생성되었습니다.");

            Post post3 = postService.write("제목3", "내용3");
            System.out.println(post3.getId() + "번 글이 생성되었습니다.");
        };
    }
}
