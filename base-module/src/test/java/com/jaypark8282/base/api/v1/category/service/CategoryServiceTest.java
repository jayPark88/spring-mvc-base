package com.jaypark8282.base.api.v1.category.service;

import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ObjectUtils;

@SpringBootTest
@ActiveProfiles("local")
class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void createCategory() {
        //given
        CategoryDto categoryDto = CategoryDto.builder()
                .name("식품 테스트")
                .description("식품 카테고리 테스트 데이터 입니다.")
                .build();

        // when
        CategoryEntity categoryEntity = categoryRepository.saveAndFlush(CategoryEntity.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build());

        // then
        Assertions.assertAll(()-> Assertions.assertFalse(ObjectUtils.isEmpty(categoryEntity)),
                () -> categoryEntity.getName().equals("식품 테스트"),
                () -> categoryEntity.getDescription().equals("식품 카테고리 테스트 데이터 입니다."));

        // 생성 후 데이터 삭제
        categoryRepository.delete(categoryEntity);
        // 데이터 삭제 확인
        Assertions.assertFalse(categoryRepository.findById(categoryEntity.getCategorySeq()).isPresent());
    }
}