package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.post.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    void T1() {
        if (postRepository.count() > 1) return;

        postService.write("글1", "내용1");
        postService.write("글2", "내용2");
    }
}
