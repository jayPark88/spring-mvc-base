package com.jaypark8282.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jaypark8282.core.jpa.entity.ProductEntity;
import com.jaypark8282.core.jpa.intf.ChangeableToFromEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
public class ProductModel implements ChangeableToFromEntity<ProductEntity> {

    private Long productSeq;
    private Long fileSeq;
    private String name;
    private String description;
    private Long price;
    private Long stockQuantity;
    private Long categorySeq;
    private String status;

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

    public ProductModel(ProductEntity entity) {
        from(entity);
    }

    @Override
    public ProductEntity to() {
        return ProductEntity.builder()
                .productSeq(productSeq)
                .fileSeq(fileSeq)
                .name(name)
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .categorySeq(categorySeq)
                .status(status)
                .build();
    }

    @Override
    public void from(ProductEntity entity) {
        this.productSeq = entity.getProductSeq();
        this.fileSeq = entity.getFileSeq();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
        this.categorySeq = entity.getCategorySeq();
        this.status = entity.getStatus();
    }

}
