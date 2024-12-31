package com.ll.jap.global.initData;

import com.ll.jap.domain.post.comment.entitiy.PostComment;
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

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final PostCommentService postCommentService;
    @Autowired
    @Lazy // 영상봤는데 뭔말씀인지 하나도 모르겠습니다
    private BaseInitData self;

    @Bean
    public ApplicationRunner baseInitData1ApplicationRunner(PostCommentService postCommentService) {

        return args -> {
            self.work1();
            self.work2();
            self.work3();
        };
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) return;

        Post post1 = postService.write("title1", "content1");
        Post post2 = postService.write("title2", "content2");
        Post post3 = postService.write("title3", "content3");

        post1.setTitle("title1-1");

        post1.addComment("comment1-1");
        post1.addComment("comment1-2");
        post2.addComment("comment2-1");
    }

    @Transactional
    public void work2() {
        Post post1 = postService.findById(1).get();
        Optional<PostComment> opPostComment1 = post1.getComments()
                .stream()
                .filter(postComment -> postComment.getId() == 1)
                .findFirst();

        if (opPostComment1.isEmpty()) return;

        PostComment postComment = opPostComment1.get();

        post1.removeComment(postComment);
    }

    @Transactional
    public void work3 () {
    }
}
