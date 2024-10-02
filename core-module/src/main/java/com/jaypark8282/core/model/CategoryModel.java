package com.jaypark8282.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jaypark8282.core.jpa.entity.CategoryEntity;
import com.jaypark8282.core.jpa.intf.ChangeableToFromEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements ChangeableToFromEntity<CategoryEntity> {

    private Long categorySeq;
    private String name;
    private String description;
    @JsonIgnore(value = true)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)//json데이터를 localdatetime으로 변경 시 사용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    @JsonIgnore(value = true)
    private String modifyId;

    @JsonIgnore(value = true)
    private String modifyNm;

    @JsonIgnore(value = true)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDatetime;

    public CategoryModel(CategoryEntity entity) {
        from(entity);
    }

    @Override
    public CategoryEntity to() {
        return CategoryEntity.builder()
                .categorySeq(categorySeq)
                .name(name)
                .description(description)
                .build();
    }

    @Override
    public void from(CategoryEntity entity) {
        this.categorySeq = entity.getCategorySeq();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
