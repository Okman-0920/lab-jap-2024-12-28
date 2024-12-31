package com.ll.jap.global.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@MappedSuperclass // 암기: 엔티티가 상속하는 클래스는 해당 어노테이션을 붙인다
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// 암기: equals(), hashCode() 메서드를 생성하는 메서드
// 암기: 동일 객체를 비교할 때 특정 조건만 비교할 수 있도록 허용하는 어노테이션
@EntityListeners(AuditingEntityListener.class)
// 암기: 엔티티가 생성 or 수정되면, createDate 필드가 자동으로 생성
public class BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifyAt;
}
