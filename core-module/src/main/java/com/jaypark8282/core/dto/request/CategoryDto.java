package com.jaypark8282.core.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long categorySeq;
    @NotNull(message = "{category.name.not.null}")
    private String name;
    private String description;
}
