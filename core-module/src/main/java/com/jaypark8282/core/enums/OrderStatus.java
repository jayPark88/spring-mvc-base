package com.jaypark8282.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    N00("N00", "입금전"),
    N10("N10", "상품준비중"),
    N20("N20", "배송준비중"),
    N21("N21", "배송대기"),
    N22("N22", "배송보류"),
    N30("N30", "배송중"),
    N40("N40", "배송완료"),
    N50("N50", "구매확정"),

    C00("C00", "취소신청"),
    C10("C10", "취소접수 - 관리자"),
    C11("C11", "취소접수거부 - 관리자"),
    C34("C34", "취소처리중 - 환불전"),
    C35("C35", "취소처리중 - 환불완료"),
    C36("C36", "취소처리중 - 환불보류"),
    C40("C40", "취소완료"),
    C41("C41", "취소 완료 - 환불전"),
    C42("C42", "취소 완료 - 환불요청중"),
    C43("C43", "취소 완료 - 환불보류"),
    C47("C47", "입금전취소 - 구매자"),
    C48("C48", "입금전취소 - 자동취소"),
    C49("C49", "입금전취소 - 관리자"),

    R00("R00", "반품신청"),
    R10("R10", "반품접수"),
    R11("R11", "반품 접수 거부"),
    R12("R12", "반품보류"),
    R13("R13", "반품접수 - 수거완료(자동)"),
    R20("R20", "반품 수거 완료"),
    R30("R30", "반품처리중 - 수거전"),
    R31("R31", "반품처리중 - 수거완료"),
    R34("R34", "반품처리중 - 환불전"),
    R36("R36", "반품처리중 - 환불보류"),
    R40("R40", "반품완료 - 환불완료"),
    R41("R41", "반품완료 - 환불전"),
    R42("R42", "반품완료 - 환불요청중"),
    R43("R43", "반품완료 - 환불보류"),

    E00("E00", "교환신청"),
    E10("E10", "교환접수"),
    N01("N01", "교환접수 - 교환상품"),
    E11("E11", "교환접수거부"),
    E12("E12", "교환보류"),
    E13("E13", "교환접수 - 수거완료(자동)"),
    E20("E20", "교환준비"),
    E30("E30", "교환처리중 - 수거전"),
    E31("E31", "교환처리중 - 수거완료"),
    E32("E32", "교환처리중 - 입금전"),
    E33("E33", "교환처리중 - 입금완료"),
    E34("E34", "교환처리중 - 환불전"),
    E35("E35", "교환처리중 - 환불완료"),
    E36("E36", "교환처리중 - 환불보류"),
    E40("E40", "교환완료");

    private final String code;
    private final String description;

    // 코드로 Enum 찾기
    public static OrderStatus fromCode(String code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
