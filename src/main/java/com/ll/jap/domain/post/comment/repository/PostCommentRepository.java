package com.ll.jap.domain.post.comment.repository;

import com.ll.jap.domain.post.comment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
