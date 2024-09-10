package com.jaypark8282.base.api.v1.order.service;

import com.jaypark8282.base.api.v1.order.dto.OrderRequestDto;
import com.jaypark8282.core.jpa.repository.OrderRepository;
import com.jaypark8282.core.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     *
     * @param orderRequestDto
     * @return
     */
    @Transactional
    public OrderModel createOrderInfo(OrderRequestDto orderRequestDto){
        OrderModel orderModel = OrderModel.builder()
                .userId(orderRequestDto.getUserId())
                .totalAmount(orderRequestDto.getTotalAmount())
                .status(orderRequestDto.getStatus())
                .paymentMethod(orderRequestDto.getPaymentMethod())
                .shippingAddress(orderRequestDto.getShippingAddress())
                .orderDateTime(LocalDateTime.now())
                .build();

        orderRepository.save(orderModel.to());
        return orderModel;
    }
}
