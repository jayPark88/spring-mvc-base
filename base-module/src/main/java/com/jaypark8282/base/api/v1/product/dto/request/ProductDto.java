package com.jaypark8282.base.api.v1.product.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long fileSeq;
    @NotNull(message = "{product.name.not.null}")
    @Size(max =255, message = "{product.name.size}")
    private String name;
    @Size(max =255, message = "{product.description.size}")
    private String description;
    @NotNull(message = "{product.price.not.null}")
    private Long price;
    @NotNull(message = "{product.stockQuantity.not.null}")
    private Long stockQuantity;
    @NotNull(message = "{product.categorySeq.not.null}")
    private Long categorySeq;
}
