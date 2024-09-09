package com.jaypark8282.base.api.v1.order.service;

import com.jaypark8282.core.enums.OrderStatus;
import com.jaypark8282.core.enums.OrderPayment;
import com.jaypark8282.core.jpa.entity.OrderInfoEntity;
import com.jaypark8282.core.jpa.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("local")
class OrderServiceTest {
    @Autowired
    OrderRepository orderRepository;
    Long orderId;

    @Test
    void createOrderInfo() {
        // given
        OrderInfoEntity orderInfoEntity = OrderInfoEntity.builder()
                .userId("parker-pen")
                .totalAmount(20000000L)
                .status(OrderStatus.N00.getCode())
                .paymentMethod(OrderPayment.CARD.code())
                .shippingAddress("경기도 성남시 판교역")
                .orderDateTime(LocalDateTime.now())
                .build();

        // when
        OrderInfoEntity saveOrderInfoEntity = orderRepository.saveAndFlush(orderInfoEntity);
        orderId = saveOrderInfoEntity.getOrderSeq();

        // then
        Assertions.assertAll(
                () -> Assertions.assertFalse(ObjectUtils.isEmpty(saveOrderInfoEntity)),
                () -> Assertions.assertEquals(saveOrderInfoEntity.getUserId(), "parker-pen")
        );

    }

    @AfterEach
    void afterEach(){
        Optional<OrderInfoEntity> optionalOrderEntity = orderRepository.findById(orderId);
        if(optionalOrderEntity.isPresent()){
            orderRepository.delete(optionalOrderEntity.get());
        }
    }
}