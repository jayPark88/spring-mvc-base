package com.jaypark8282.base.api.v1.category.service;

import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;
    private CategoryEntity categoryEntity;
    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void beforeEach(){
        categoryEntity = createCategoryEntity("");
    }

    @AfterEach
    void afterEach(){
        categoryRepository.delete(categoryEntity);
    }

    @Test
    void createCategory() {
        // then
        Assertions.assertAll(()-> Assertions.assertFalse(ObjectUtils.isEmpty(categoryEntity)),
                () -> categoryEntity.getName().equals("식품 테스트"),
                () -> categoryEntity.getDescription().equals("식품 카테고리 테스트 데이터 입니다."));

        // 생성 후 데이터 삭제
        categoryRepository.delete(categoryEntity);
        // 데이터 삭제 확인
        Assertions.assertFalse(categoryRepository.findById(categoryEntity.getCategorySeq()).isPresent());
    }

    @Test
    void searchCategoryList() {
        // given
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for(int i=0; i<11; i++){
            categoryEntityList.add(createCategoryEntity(String.valueOf(i)));
        }

        int page = 0;
        int size =10;

        // when
        Page<CategoryEntity> categoryEntityPage = categoryService.searchCategoryList(page, size);

        // then
        Assertions.assertAll(
                () -> Assertions.assertFalse(categoryEntityPage.isEmpty()),
                () -> Assertions.assertTrue(categoryEntityPage.getContent().stream().filter(item -> item.getName().equals("식품 테스트2")).findFirst().isPresent())
        );


    }

    private CategoryEntity createCategoryEntity(String index){
        //given
        CategoryDto categoryDto = CategoryDto.builder()
                .name("식품 테스트"+index)
                .description("식품 카테고리 테스트 데이터 입니다.")
                .build();

        // when
        return categoryRepository.saveAndFlush(CategoryEntity.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build());
    }
}