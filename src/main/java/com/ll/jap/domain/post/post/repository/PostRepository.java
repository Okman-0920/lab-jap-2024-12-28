package com.ll.jap.domain.post.post.repository;

import com.ll.jap.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);

    List<Post> findByTitleAndContent(String title, String content);

    List<Post> findByTitleLike(String title);

    List<Post> findByTitleLikeOrderByIdDesc(String string);

    List<Post> findByOrderByIdDesc();

    List<Post> findTop2ByTitleLikeOrderByIdDesc(String title);

    List<Post> findTop2ByOrderByIdDesc();
}
