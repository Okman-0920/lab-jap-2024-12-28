package com.ll.jap.global.initData;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitData1ApplicationRunner() {

        return args -> {
            if (postService.count() > 0) return;

            Post post1 = postService.write("제목1", "내용1");
            Post post2 = postService.write("제목2", "내용2");
            Post post3 = postService.write("제목3", "내용3");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitData2ApplicationRunner() {

        return args -> {
            if (postService.count() > 0) return;

            Post post1 = postService.write("제목1", "내용1");
            Post post2 = postService.write("제목2", "내용2");
            Post post3 = postService.write("제목3", "내용3");
        };
    }
}
