package com.ll.jap.global.initData;

import com.ll.jap.domain.post.comment.entitiy.PostComment;
import com.ll.jap.domain.post.comment.service.PostCommentService;
import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final PostCommentService postCommentService;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitData1ApplicationRunner(PostCommentService postCommentService) {

        return args -> {
            if (postService.count() > 0) return;

            Post post1 = postService.write("제목1", "내용1");
            Post post2 = postService.write("제목2", "내용2");
            Post post3 = postService.write("제목3", "내용3");

            PostComment postComment1 = postCommentService.write(post1, "글1의 댓글1");
            PostComment postComment2 = postCommentService.write(post1, "글1의 댓글2");
            PostComment postComment3 = postCommentService.write(post2, "글2의 댓글1");

            Post postOfComment3 = postComment3.getPost();
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitData2ApplicationRunner(PostCommentService postCommentService) {

        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                PostComment postComment3 = postCommentService.findById(3).get();

                Post postOfComment3 = postComment3.getPost();
                System.out.println("postOfComment3.id = " + postOfComment3.getId());
                // 쿼리x

                System.out.println("postOfComment3.title = " + postOfComment3.getTitle());
                // 쿼리 발생

                System.out.println("postOfComment3.content = " + postOfComment3.getContent());
                // 쿼리x
            }
        };
    }
}
