package com.jaypark8282.base.api.v1.sample.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleGetRequestDto {

    @NotNull
    private String nat;
}
