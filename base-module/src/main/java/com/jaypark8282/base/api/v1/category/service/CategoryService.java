package com.jaypark8282.base.api.v1.category.service;

import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.repository.CategoryRepository;
import com.jaypark8282.core.model.CategoryModel;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;

    @Transactional
    public CategoryModel createCategory(CategoryDto categoryDto) {
        return Optional.of(categoryDto)
                .map(dto -> CategoryEntity.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .build())
                .map(categoryRepository::saveAndFlush)
                .map(savedEntity -> {
                    CategoryModel model = new CategoryModel();
                    model.from(savedEntity);
                    return model;
                })
                .orElseThrow(() -> new CustomException(FAIL_500.code(), messageSource.getMessage("category.save.fail",null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Transactional
    public CategoryModel updateCategory(CategoryDto categoryDto) {
        return categoryRepository.findById(categoryDto.getCategorySeq())
                .map(existingCategory -> {
                    Optional.ofNullable(categoryDto.getName()).ifPresent(existingCategory::setName);
                    Optional.ofNullable(categoryDto.getDescription()).ifPresent(existingCategory::setDescription);

                    return new CategoryModel(existingCategory);
                }).orElseThrow(() -> new CustomException(FAIL_500.code(), messageSource.getMessage("category.not.found", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Transactional(readOnly = true)
    public Page<CategoryEntity> searchCategoryList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }
}
