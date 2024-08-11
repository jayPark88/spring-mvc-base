package com.jaypark8282.base.api.v1.category.service;

import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryEntity createCategory(CategoryDto categoryDto) {
        return categoryRepository.saveAndFlush(CategoryEntity.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build());
    }

    @Transactional(readOnly = true)
    public Page<CategoryEntity> searchCategoryList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }
}
