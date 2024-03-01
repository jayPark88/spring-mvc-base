package com.jaypark8282.base.api.v1.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jaypark8282.core.util.retrofit.RetrofitClient;
import com.jaypark8282.core.util.retrofit.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {
    private final RetrofitClient retrofitClient;

    public void reqeustRetrofitTest() throws IOException {
        Call<Object> sampleInfoCall = retrofitClient.getSampleOpenApiService().getRandomUserInfo("us");
        this.convertInterfaceData(sampleInfoCall);
    }

    private void convertInterfaceData(Call<Object> sampleInfoCall) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Response<Object> resultCallIfno = sampleInfoCall.execute();
        LinkedTreeMap<String, Object> resultMap = (LinkedTreeMap<String, Object>) resultCallIfno.body();

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        log.info(String.valueOf(userDto));
    }
}
