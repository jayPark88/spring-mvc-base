package com.jaypark8282.base.api.v1.sample.controller;

import com.jaypark8282.base.api.v1.sample.dto.SampleGetRequestDto;
import com.jaypark8282.base.api.v1.sample.service.SampleService;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.resonse.CommonResponse;
import com.jaypark8282.core.util.retrofit.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_404;
import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;
    private final MessageSource messageSource;

    @Operation(summary = "sampleGetTest", description = "http://randomuser.me API연동 후 데이터 GET",
              parameters = {
        @Parameter(name = "nat", in = ParameterIn.QUERY, description = "nat", example = "us")
    })
    @GetMapping
    public CommonResponse<UserDto> getMappingTest(@ModelAttribute @Valid SampleGetRequestDto sampleGetRequestDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_404.code(), messageSource.getMessage("http.status.bad.request", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        }
        try {
            return new CommonResponse<>(sampleService.requestRetrofitTest(sampleGetRequestDto));
        } catch (IOException e) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("interfacing.trouble", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
