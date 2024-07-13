package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass// BaseInfoEntity를 상속한 엔터티들은 해당 엔터티를 데이터베이스 테이블의 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class)
public class BaseInfoEntity {

    @CreatedDate
    private LocalDateTime createdDateTime;

    @CreatedBy
    @Column(updatable = false)
    private String createId;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    @LastModifiedBy
    private String modifiedId;
}
