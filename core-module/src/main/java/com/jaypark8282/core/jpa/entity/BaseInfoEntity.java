package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass// BaseInfoEntity를 상속한 엔터티들은 해당 엔터티를 데이터베이스 테이블의 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class)
// `@EntityListeners(AuditingEntityListener.class)`는 JPA에서 엔터티의 변경 사항을 추적하고 자동으로 관리자적 정보(예: 생성일, 수정일)를 기록하기 위해 사용되는 애너테이션입니다.
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
