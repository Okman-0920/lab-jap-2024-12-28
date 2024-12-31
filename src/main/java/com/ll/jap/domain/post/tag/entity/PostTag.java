package com.ll.jap.domain.post.tag.entity;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(PostTagId.class)
public class PostTag extends BaseEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Include
    private Post post;

    @Id
    @Column(length = 30)
    @EqualsAndHashCode.Include
    private String content;
}
