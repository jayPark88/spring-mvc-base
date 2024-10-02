package com.jaypark8282.core.enums;

public enum OrderPayment {
    CASH("cash", "현금"),
    CARD("card", "카드"),
    TCASH("tcash", "게좌이체");

    OrderPayment(String code, String value) {
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
