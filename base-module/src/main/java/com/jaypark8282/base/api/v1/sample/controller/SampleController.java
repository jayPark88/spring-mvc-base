package com.jaypark8282.base.api.v1.sample.controller;

import com.jaypark8282.base.api.v1.sample.service.SampleService;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_400;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/sample")
@RestController
public class SampleController {

    private final MessageSource messageSource;
    private final SampleService sampleService;

    @GetMapping
    public CommonResponse<String> getMappingTest() {
        return new CommonResponse<>("Success!");
    }

    @GetMapping("/exception")
    public CommonResponse<String> getException() {
        throw new CustomException(FAIL_400.code(), messageSource.getMessage("http.status.bad.request", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/retrofit/test")
    public void requestRetrofitTest() throws IOException {
        sampleService.reqeustRetrofitTest();
    }
}
