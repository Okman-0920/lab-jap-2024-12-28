package com.ll.jap.domain.post.post.entity;

import com.ll.jap.domain.member.entity.Member;
import com.ll.jap.domain.post.comment.entity.PostComment;
import com.ll.jap.domain.post.tag.entity.PostTag;
import com.ll.jap.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    // 엔티티관의 관계를 설정
    private Member author;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean blind;

    @Builder.Default // 암기: @Builder 있으면 new 무시되니 붙여야 함
    @OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    // mappedBy = "@ManyToOne과 연결된 변수명"
    // 자식 클래스를 같이 저장하고 싶으면 cascade를 사용하세요
    // orphanRemoval = true : remove로 제거한거 db에서도 삭제해줘
    // PERSIST는 트랜잭션과 관련없다
    public List<PostComment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    public Set<PostTag> tags = new HashSet<>();
    // 암기 : Set<> a = new HashSet<>(); --> 자체 중복제거

    public void addComment(Member author, String content) {
        PostComment postComment = PostComment
                .builder()
                .post(this) // 지금 나(Post)의댓글이다
                .author(author)
                .content(content)
                .build();

        comments.add(postComment);
    }


    public void addTag(String content) {
//    (강사님 선호 방법)
//        Optional<PostTag> opDldPostTag = tags
//                .stream()
//                .filter(tag -> tag.getContent().equals(content))
//                .findFirst();
//
//        if (opDldPostTag.isPresent()) return;

        PostTag postTag = PostTag
                .builder()
                .post(this)
                .content(content)
                .build();

        tags.add(postTag);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
    }
}
