package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderInfo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)// 객체간 비교 시 true 할 경우 부모 필드값 까지 비교한다, 그래야 동등성이 더 명확해진다고 함
public class OrderInfoEntity extends BaseInfoEntity{
    @Id
    @Column(name = "order_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSeq;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "total_amount")
    private Long totalAmount;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;
}
