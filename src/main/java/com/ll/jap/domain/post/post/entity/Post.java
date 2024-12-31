package com.ll.jap.domain.post.post.entity;

import com.ll.jap.domain.post.comment.entitiy.PostComment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// 암기: equals(), hashCode() 메서드를 생성하는 메서드
// 암기: 동일 객체를 비교할 때 특정 조건만 비교할 수 있도록 허용하는 어노테이션
@EntityListeners(AuditingEntityListener.class)
// 암기: 엔티티가 생성 or 수정되면, createDate 필드가 자동으로 생성

public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Setter(AccessLevel.PRIVATE) // 암기: setter 못쓰게
    @EqualsAndHashCode.Include // 암기: 동일하게 만들 조건에 작성
    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE) // setter 못쓰게
    private LocalDateTime createAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE) // setter 못쓰게
    private LocalDateTime modifyAt;

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

    public void addComment(String content) {
        PostComment postComment = PostComment
                .builder()
                .post(this) // 지금 나(Post)의댓글이다
                .content(content)
                .build();

        comments.add(postComment);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
    }
}
