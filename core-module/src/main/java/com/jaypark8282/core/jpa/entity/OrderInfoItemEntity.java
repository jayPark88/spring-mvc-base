package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "orderInfoItem")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoItemEntity {
    @Id
    @Column(name = "order_info_item_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderInfoItemSeq;
    @Column(name = "order_info_seq")
    private Long orderInfoSeq;
    @Column(name = "product_seq")
    private Long productSeq;
    @Column(name = "quantity")
    private Long quantity;
}
