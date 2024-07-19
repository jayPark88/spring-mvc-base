package com.jaypark8282.base.api.v1.product.service;

import com.jaypark8282.base.api.v1.product.dto.request.ProductDto;
import com.jaypark8282.core.enums.ProductStatus;
import com.jaypark8282.core.model.ProductModel;
import com.jaypark8282.core.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MessageSource messageSource;

    @Transactional
    public ProductModel registProduct(ProductDto productDto) {
        ProductModel productModel = ProductModel.builder()
                .productSeq(0L)
                .fileSeq(productDto.getFileSeq())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stockQuantity(productDto.getStockQuantity())
                .categorySeq(productDto.getCategorySeq())
                .status(ProductStatus.COMING_SOON.code())
                .build();
        productRepository.save(productModel.to());
        return productModel;
    }
}
