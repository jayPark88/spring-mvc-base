package com.jaypark8282.base.api.v1.category.service;

import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryEntity createCategory(CategoryDto categoryDto) {
        return categoryRepository.saveAndFlush(CategoryEntity.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build());
    }
}
