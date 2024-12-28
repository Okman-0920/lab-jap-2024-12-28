package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = Post
                .builder()
                .createAt(LocalDateTime.now())
                .modifyAt(LocalDateTime.now())
                .title(title)
                .content(content)
                .build();

        postRepository.save(post);

        return post;
    }

    public Long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
