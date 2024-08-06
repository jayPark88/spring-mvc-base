package com.jaypark8282.base.api.v1.product.controller;

import com.jaypark8282.base.api.v1.product.dto.request.ProductDto;
import com.jaypark8282.base.api.v1.product.service.ProductService;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.ProductEntity;
import com.jaypark8282.core.model.ProductModel;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_404;
import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final MessageSource messageSource;
    private final ProductService productService;

    @PostMapping
    public CommonResponse<ProductModel> registProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_404.code(), messageSource.getMessage("http.status.bad.request", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        }
        try {
            return new CommonResponse<>(productService.registProduct(productDto));
        } catch (DataAccessException e) {
            log.info("signUp error {}", e.getMessage());
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("user.signup.save.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{productSeq}")
    public CommonResponse<ProductModel> updateProduct(@PathVariable("productSeq") Long productSeq, @RequestBody ProductDto productDto) {
        return new CommonResponse<>(productService.updateProduct(productSeq, productDto));
    }

    @DeleteMapping("/{productSeq}")
    public CommonResponse<String> deleteProductInfo(@PathVariable("productSeq") Long productSeq) {
        try {
            return new CommonResponse<>(productService.deleteProductInfo(productSeq));
        } catch (RuntimeException e) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("user.delete.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public CommonResponse<Page<ProductEntity>> searchProductList(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                 @RequestParam(defaultValue = "10", name = "size") int size) {
        return new CommonResponse<>(productService.searchProductList(page, size));
    }

    @GetMapping("/{productSeq}")
    public CommonResponse<ProductEntity> getUserInfo(@PathVariable(name = "productSeq") Long productSeq) {
        return new CommonResponse<>(productService.getProductInfo(productSeq).orElseThrow(() -> new CustomException(FAIL_500.code(), messageSource.getMessage("product.not.found", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR)));
    }
}
