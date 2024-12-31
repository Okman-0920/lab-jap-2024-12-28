package com.ll.jap.domain.post.tag.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode // 암기: 룰이니까 그냥 넣으세요...
public class PostTagId implements Serializable {
    private Long post;
    private String content;
}
