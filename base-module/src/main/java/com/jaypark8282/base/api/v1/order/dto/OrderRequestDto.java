package com.jaypark8282.base.api.v1.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "{order.user.id.not.null}")
    private String userId;
    @NotNull(message = "{order.total.amount.not.null}")
    private Long totalAmount;
    @NotNull(message = "{order.status.not.null}")
    private String status;
    @NotNull(message = "{order.payment.method.not.null}")
    private String paymentMethod;
    @NotNull(message = "{order.shipping.address.not.null}")
    private String shippingAddress;
    private LocalDateTime orderDateTime;
}
