package com.jaypark8282.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jaypark8282.core.jpa.entity.OrderInfoEntity;
import com.jaypark8282.core.jpa.intf.ChangeableToFromEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel implements ChangeableToFromEntity<OrderInfoEntity> {

    private Long orderSeq;
    private String userId;
    private Long totalAmount;
    private String status;
    private String paymentMethod;
    private String shippingAddress;
    private LocalDateTime orderDateTime;
    @JsonIgnore(value = true)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)//json데이터를 localdatetime으로 변경 시 사용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    @JsonIgnore(value = true)
    private String modifyId;

    @JsonIgnore(value = true)
    private String modifyNm;

    @JsonIgnore(value = true)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDatetime;

    public OrderModel(OrderInfoEntity entity) {
        from(entity);
    }

    @Override
    public OrderInfoEntity to() {
        return OrderInfoEntity.builder()
                .orderSeq(orderSeq)
                .userId(userId)
                .totalAmount(totalAmount)
                .status(status)
                .paymentMethod(paymentMethod)
                .shippingAddress(shippingAddress)
                .orderDateTime(orderDateTime)
                .build();
    }

    @Override
    public void from(OrderInfoEntity entity) {
        orderSeq = entity.getOrderSeq();
        userId = entity.getUserId();
        totalAmount = entity.getTotalAmount();
        status = entity.getStatus();
        paymentMethod = entity.getPaymentMethod();
        shippingAddress = entity.getShippingAddress();
        orderDateTime = entity.getOrderDateTime();
    }
}
