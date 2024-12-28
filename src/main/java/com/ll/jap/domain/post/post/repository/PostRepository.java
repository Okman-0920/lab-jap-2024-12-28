package com.ll.jap.domain.post.post.repository;

import com.ll.jap.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
