package com.jaypark8282.core.enums;

public enum ProductStatus {
    FOR_SALE("FOR_SALE", "판매중"),
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
