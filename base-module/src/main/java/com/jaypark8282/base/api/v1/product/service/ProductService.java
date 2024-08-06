package com.jaypark8282.base.api.v1.product.service;

import com.jaypark8282.base.api.v1.product.dto.request.ProductDto;
import com.jaypark8282.core.enums.ProductStatus;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.ProductEntity;
import com.jaypark8282.core.jpa.repository.ProductRepository;
import com.jaypark8282.core.model.ProductModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;


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

        productModel.updateProductSeq(productRepository.saveAndFlush(productModel.to()).getProductSeq());
        return productModel;
    }

    @Transactional
    public ProductModel updateProduct(Long productSeq, ProductDto productDto) {
        return productRepository.findById(productSeq)
                .map(existingProduct -> {
                    Optional.ofNullable(productDto.getFileSeq())
                            .ifPresent(existingProduct::setFileSeq);

                    Optional.ofNullable(productDto.getName())
                            .ifPresent(existingProduct::setName);

                    Optional.ofNullable(productDto.getDescription())
                            .ifPresent(existingProduct::setDescription);

                    Optional.ofNullable(productDto.getDescription())
                            .ifPresent(existingProduct::setDescription);

                    Optional.ofNullable(productDto.getPrice())
                            .ifPresent(existingProduct::setPrice);

                    Optional.ofNullable(productDto.getStockQuantity())
                            .ifPresent(existingProduct::setStockQuantity);

                    Optional.ofNullable(productDto.getCategorySeq())
                            .ifPresent(existingProduct::setCategorySeq);

                    Optional.ofNullable(productDto.getStatus())
                            .ifPresent(existingProduct::setStatus);

                    return new ProductModel(existingProduct);
                }).orElseThrow(() -> new CustomException(FAIL_500.code(), messageSource.getMessage("product.not.found", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Transactional
    public String deleteProductInfo(Long productSeq) {
        productRepository.deleteById(productSeq);
        return productSeq + " deleted!";
    }

    @Transactional(readOnly = true)
    public Page<ProductEntity> searchProductList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> getProductInfo(Long productSeq) {
        return productRepository.findById(productSeq);
    }
}
