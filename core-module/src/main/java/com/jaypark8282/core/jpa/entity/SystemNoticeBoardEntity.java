//package com.jaypark8282.core.jpa.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.DiscriminatorValue;
//import jakarta.persistence.Entity;
//import lombok.Builder;
//import org.hibernate.annotations.Comment;
//
//import java.time.LocalDateTime;
//
//@Builder
//@Entity
//@DiscriminatorValue("System")
//public class SystemNoticeBoardEntity extends NoticeBoardEntity {
//
//    @Comment("시스템 버전")
//    @Column(name = "system_version")
//    private String systemVersion;
//
//    @Comment("시스템 업데이트 일자")
//    @Column(name = "upgrade_date_time")
//    private LocalDateTime upgradeDateTime;
//
//    @Comment("예정된 서비스 다운타임")
//    @Column(name = "scheduled_downtime")
//    private LocalDateTime scheduledDowntime;
//}