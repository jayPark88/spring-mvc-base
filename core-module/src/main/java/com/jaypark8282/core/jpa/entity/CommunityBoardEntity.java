//package com.jaypark8282.core.jpa.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.DiscriminatorValue;
//import jakarta.persistence.Entity;
//import lombok.Builder;
//import org.hibernate.annotations.Comment;
//
//@Entity
//@Builder
//@DiscriminatorValue("Community")// 자식 객체를 구분할 값은 기본적으로 클래스명으로 설정됩니다.
//public class CommunityBoardEntity extends BoardEntity {
//
//    @Comment("썸네일 이미지")
//    @Column(columnDefinition = "TEXT")
//    private String thumbNail;
//
//    @Comment("추천")
//    @Column(name = "recommend_count")
//    private Long recommendCount;
//
//    @Comment("반대")
//    @Column(name = "contrary_count")
//    private Long contraryCount;
//
//    @Comment("조회 수")
//    @Column(name = "hit_count")
//    private Long hitCount;
//}
