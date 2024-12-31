package com.ll.jap.domain.member.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Member {
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

    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 100)
    private String password;

    private String nickname;
}
