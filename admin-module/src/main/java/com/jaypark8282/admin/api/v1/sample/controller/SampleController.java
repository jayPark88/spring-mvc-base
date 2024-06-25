package com.jaypark8282.admin.api.v1.sample.controller;

import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sample")
@RequiredArgsConstructor
public class SampleController {

    @GetMapping
    public CommonResponse<String> getMappingTest() {
        return new CommonResponse<>("Success");
    }
}
