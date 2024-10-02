package com.jaypark8282.base.api.v1.file.controller;

import com.jaypark8282.base.api.v1.login.service.AuthService;
import com.jaypark8282.core.dto.LoginDto;
import com.jaypark8282.core.dto.TokenDto;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Slf4j
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthService authService;
    private ClassPathResource resource;
    private LoginDto loginDto;

    @BeforeEach
    public void setUp() {
        // 파일 리소스를 ClassPathResource로 로드
        resource = new ClassPathResource("/base_project.png");
        // 원래 이런 민감한 데이터는 vault로 관리하는 것이 맞다.
        loginDto = new LoginDto("parker-pen", "skfnxhskfnxh12a!");
    }

    @Test
    public void testLogin() throws Exception {
        // when
        CommonResponse<TokenDto> commonResponse = authService.authorize(loginDto);

        log.info("jwtToken!! {}", commonResponse.getData().getToken());
        // then
        Assertions.assertAll(
                () -> Assertions.assertFalse(ObjectUtils.isEmpty(commonResponse)),
                () -> Assertions.assertFalse(ObjectUtils.isEmpty(commonResponse.getData().getToken()))
        );
    }

    @Test
    void uploadFile() {
        //given
        try {
            MockMultipartFile multipartFile = generateMultipartFile(resource);

            mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/file/upload")
                            .file(multipartFile)
                            .header(HttpHeaders.AUTHORIZATION, "Bearer "+requestToken()) // 헤더 추가
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)) // 추가 헤더
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").value(true)) // success 필드의 값이 true인지 확인
                    .andExpect(jsonPath("$.data").exists()) // data 필드가 존재하는지 확인
                    .andExpect(jsonPath("$.data.name").value("base_project.png")); // data.fileName 필드의

        } catch (Exception e) {
            Assertions.fail("uploadFile Test Fail");
        }
    }

    private MockMultipartFile generateMultipartFile(ClassPathResource resource) throws IOException {
        return new MockMultipartFile(
                "file",
                resource.getFilename(),
                Files.probeContentType(resource.getFile().toPath()),
                resource.getInputStream()
        );
    }

    private String requestToken(){
        CommonResponse<TokenDto> commonResponse = authService.authorize(loginDto);
        if(ObjectUtils.isEmpty(commonResponse.getData()) || ObjectUtils.isEmpty(commonResponse.getData().getToken())){
            throw new NullPointerException();
        }else{
            return commonResponse.getData().getToken();
        }
    }
}