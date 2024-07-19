package com.jaypark8282.core.enums;

public enum ProductStatus {
    IN_STOCK("IN_STOCK", "재고가 있는 상태"),
    OUT_OF_STOCK("OUT_OF_STOCK", "재고가 없는 상태"),
    DISCONTINUED("DISCONTINUED", "단종된상태"),
    PRE_ORDER("PRE_ORDER", "예약 주문 상태"),
    COMING_SOON("COMING_SOON", "곧 출시 예정 상태");

    ProductStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private final String code;
    private final String value;

    public String code() {
        return code;
    }

    public String value() {
        return value;
    }
}
