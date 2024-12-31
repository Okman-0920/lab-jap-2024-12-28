package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.member.member.Member;
import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 질문: Member author
    // Member 인스턴스 변수의 값을 가져오는것인지, 클래스인지?
    public Post write(Member author, String title, String content) {
        Post post = Post
                .builder()
                .author(author)
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

    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleAndContent(String title, String content) {
        return postRepository.findByTitleAndContent(title, content);
    }

    public List<Post> findByTitleLike(String title) {
        return postRepository.findByTitleLike(title);
    }

    public List<Post> findByTitleLikeOrderByIdDesc(String String) {
        return postRepository.findByTitleLikeOrderByIdDesc(String);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }

    public List<Post> findTop2ByTitleLikeOrderByIdDesc(String title) {
        return postRepository.findTop2ByTitleLikeOrderByIdDesc(title);
    }

    public List<Post> findTop2ByOrderByIdDesc() {
        return postRepository.findTop2ByOrderByIdDesc();
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> findByTitleLike(String title, Pageable pageable) {
        return postRepository.findByTitleLike(title, pageable);
    }
}
