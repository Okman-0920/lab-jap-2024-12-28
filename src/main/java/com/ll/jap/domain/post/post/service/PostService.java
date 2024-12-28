package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = new Post();
        post.setCreateAt(LocalDateTime.now());
        post.setModifyAt(LocalDateTime.now());
        post.setTitle(title);
        post.setContent(content);
        post.setBlind(false);

        postRepository.save(post);

        return post;
    }

    public Long count() {
        return postRepository.count();
    }
}
