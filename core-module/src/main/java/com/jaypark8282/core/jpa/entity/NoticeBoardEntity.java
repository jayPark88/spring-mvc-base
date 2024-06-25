//package com.jaypark8282.core.jpa.entity;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.Comment;
//
//import java.time.LocalDateTime;
//
//@Entity
//@DiscriminatorColumn(name = "Notice")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public class NoticeBoardEntity extends BoardEntity {
//    @Comment("마감일")
//    @Column(name = "expiration_Date_Time")
//    private LocalDateTime expirationDateTime;
//    @Comment("우선순위")
//    private Long priority;
//}
