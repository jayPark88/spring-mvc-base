package com.jaypark8282.base.api.v1.category.controller;

import com.jaypark8282.base.api.v1.category.service.CategoryService;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryContorller {
    private final MessageSource messageSource;
    private final CategoryService categoryService;

    @PostMapping
    public CommonResponse
}
