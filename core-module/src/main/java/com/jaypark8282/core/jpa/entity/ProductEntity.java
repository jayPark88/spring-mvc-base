package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)// 객체간 비교 시 true 할 경우 부모 필드값 까지 비교한다, 그래야 동등성이 더 명확해진다고 함
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity extends BaseInfoEntity {

    @Id
    @Column(name = "product_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;
    @Column(name = "file_seq")
    private Long fileSeq;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private String price;
    @Column(name = "stock_quantity")
    private String stockQuantity;
    @Column(name = "category_seq")
    private Long categorySeq;
}
