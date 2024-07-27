package com.jaypark8282.base.api.v1.file.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("local")
class FileServiceTest {

    private ClassPathResource resource;

    @BeforeEach
    public void setUp() {
        // 파일 리소스를 ClassPathResource로 로드
        resource = new ClassPathResource("/base_project.png");

    }

    @Test
    public void testLoadFile() throws IOException {
        // 파일이 존재하는지 확인
        assertNotNull(resource);

        // MockMultipartFile로 변환
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                resource.getFilename(),
                Files.probeContentType(resource.getFile().toPath()),
                resource.getInputStream()
        );

        // 파일이 제대로 로드되었는지 확인
        assertNotNull(multipartFile);
        System.out.println("MultipartFile name: " + multipartFile.getName());
        System.out.println("MultipartFile original name: " + multipartFile.getOriginalFilename());
        System.out.println("MultipartFile content type: " + multipartFile.getContentType());
    }

    @Test
    public void fileUploadTest(){

    }


    private MockMultipartFile generateMultipartFile(ClassPathResource resource) throws IOException {
        return new MockMultipartFile(
                "file",
                resource.getFilename(),
                Files.probeContentType(resource.getFile().toPath()),
                resource.getInputStream()
        );
    }

}