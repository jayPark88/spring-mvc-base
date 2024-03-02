package com.jaypark8282.base.api.v1.sample.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SampleGetRequestDto {

    @NotNull
    private String nat;
}
