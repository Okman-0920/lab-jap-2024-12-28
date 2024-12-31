package com.ll.jap.global.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 암기: 엔티티가 상속하는 클래스는 해당 어노테이션을 붙인다
@EntityListeners(AuditingEntityListener.class)
// 암기: 엔티티가 생성 or 수정되면, createDate 필드가 자동으로 생성
public class BaseTime extends BaseEntity {
    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifyAt;
}
