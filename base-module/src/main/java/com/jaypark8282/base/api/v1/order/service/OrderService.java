package com.jaypark8282.base.api.v1.order.service;

import com.jaypark8282.base.api.v1.order.dto.OrderRequestDto;
import com.jaypark8282.core.jpa.entity.OrderInfoEntity;
import com.jaypark8282.core.jpa.repository.OrderInfoRepository;
import com.jaypark8282.core.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderInfoRepository orderInfoRepository;

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

        orderInfoRepository.save(orderModel.to());
        return orderModel;
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public Page<OrderInfoEntity> searchOrderInfoList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderInfoRepository.findAll(pageable);
    }
}
