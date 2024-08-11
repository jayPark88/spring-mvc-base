package com.jaypark8282.core.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long categorySeq;
    private String name;
    private String description;
}
