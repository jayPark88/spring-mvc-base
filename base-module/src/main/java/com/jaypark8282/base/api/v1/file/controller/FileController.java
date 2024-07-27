package com.jaypark8282.base.api.v1.file.controller;

import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.FileEntity;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final MessageSource messageSource;

    @PostMapping("/upload")
    public CommonResponse<FileEntity> uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("file.not.null", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new CommonResponse<>(FileEntity.builder().name(file.getOriginalFilename()).build());
    }
}