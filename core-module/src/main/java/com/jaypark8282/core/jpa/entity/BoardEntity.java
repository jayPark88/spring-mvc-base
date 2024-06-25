//package com.jaypark8282.core.jpa.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Comment;
//
//@Getter
//@Setter
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "board_type")// default는 Dtype으로 설정 됨.
//public abstract class BoardEntity extends BaseInfoEntity {
//
//
//    /*protected 생성자를 사용하는 이유:
//     기본생성자는 객체에서 무조건 필수 인데 외부에서 직접 생성되지 않도록 하기 위함.
//      JPA가 내부에서 엔터티를 생성할 때 사용 가능하도록 함.*/
//    protected BoardEntity() {
//    }
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Comment("제목")
//    private String title;
//
//    @Comment("내용")
//    @Column(columnDefinition = "TEXT")
//    private String content;
//
//    public BoardEntity(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//}
