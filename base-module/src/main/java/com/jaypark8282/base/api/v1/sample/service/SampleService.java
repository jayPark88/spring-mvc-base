package com.jaypark8282.base.api.v1.sample.service;

import com.jaypark8282.base.api.v1.sample.dto.SampleGetRequestDto;
import com.jaypark8282.core.util.retrofit.RetrofitClient;
import com.jaypark8282.core.util.retrofit.dto.UserDto;
import com.jaypark8282.core.util.retrofit.service.RetrofitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {
    private final RetrofitClient retrofitClient;
    private final RetrofitService retrofitService;

    public UserDto requestRetrofitTest(SampleGetRequestDto sampleGetRequestDto) throws IOException {
        Call<Object> sampleInfoCall = retrofitClient.getSampleOpenApiService().getRandomUserInfo(sampleGetRequestDto.getNat());
        return retrofitService.convertInterfaceData(sampleInfoCall, UserDto.class);
    }
}
