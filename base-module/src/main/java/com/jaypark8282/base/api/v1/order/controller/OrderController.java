package com.jaypark8282.base.api.v1.order.controller;

import com.jaypark8282.base.api.v1.order.dto.OrderRequestDto;
import com.jaypark8282.base.api.v1.order.service.OrderService;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.OrderInfoEntity;
import com.jaypark8282.core.jpa.entity.ProductEntity;
import com.jaypark8282.core.model.OrderModel;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_404;
import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final MessageSource messageSource;

    @PostMapping
    public CommonResponse<OrderModel> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_404.code(),
                    messageSource.getMessage("http.status.bad.request", null, LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            return new CommonResponse<>(orderService.createOrderInfo(orderRequestDto));
        } catch (DataAccessException e) {
            log.error("Order creation failed: {}", e.getMessage(), e);
            throw new CustomException(FAIL_500.code(),
                    messageSource.getMessage("order.save.fail", null, LocaleContextHolder.getLocale()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public CommonResponse<Page<OrderInfoEntity>> searchOrderInfoList(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                 @RequestParam(defaultValue = "10", name = "size") int size) {
        return new CommonResponse<>(orderService.searchOrderInfoList(page, size));
    }
}
