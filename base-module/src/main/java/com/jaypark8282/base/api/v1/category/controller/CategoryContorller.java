package com.jaypark8282.base.api.v1.category.controller;

import com.jaypark8282.base.api.v1.category.service.CategoryService;
import com.jaypark8282.core.dto.request.CategoryDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.model.CategoryModel;
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

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryContorller {
    private final MessageSource messageSource;
    private final CategoryService categoryService;

    /**
     *
     * @param categoryDto
     * @param bindingResult
     * @return
     */
    @PostMapping
    public CommonResponse<CategoryModel> createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("category.name.not.null", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            return new CommonResponse<>(categoryService.createCategory(categoryDto));
        } catch (DataAccessException e) {
            log.info("Category Create {}", e.getMessage());
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("data.insert.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param categorySeq
     * @param categoryDto
     * @param bindingResult
     * @return
     */
    @PatchMapping("/{categorySeq}")
    public CommonResponse<CategoryModel> updateCategory(@PathVariable("categorySeq")Long categorySeq, @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("category.name.not.null", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            categoryDto.setCategorySeq(categorySeq);
            return new CommonResponse<>(categoryService.updateCategory(categoryDto));
        } catch (DataAccessException e) {
            log.info("Category update {}", e.getMessage());
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("data.insert.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{categorySeq}")
    public CommonResponse<String> deleteCategoryInfo(@PathVariable("categorySeq") Long categorySeq) {
        try {
            return new CommonResponse<>(categoryService.deleteCategoryInfo(categorySeq));
        } catch (RuntimeException e) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("category.delete.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public CommonResponse<Page<CategoryEntity>> searchCategoryList(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                   @RequestParam(defaultValue = "10", name = "size") int size) {
        return new CommonResponse<>(categoryService.searchCategoryList(page, size));
    }

}
