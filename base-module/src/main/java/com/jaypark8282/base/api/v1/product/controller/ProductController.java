package com.jaypark8282.base.api.v1.product.controller;

import com.jaypark8282.base.api.v1.product.dto.request.ProductDto;
import com.jaypark8282.base.api.v1.product.service.ProductService;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.model.ProductModel;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
