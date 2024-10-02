package com.jaypark8282.core.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long categorySeq;
    @NotNull(message = "{category.name.not.null}")
    private String name;
    private String description;
}
