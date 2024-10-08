package com.jaypark8282.base.api.v1.file.controller;

import com.jaypark8282.base.api.v1.file.service.FileService;
import com.jaypark8282.core.dto.request.FileDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.FileEntity;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;

@RestController
@RequestMapping("/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final MessageSource messageSource;
    private final FileService fileService;

    @PostMapping("/upload")
    public CommonResponse<List<FileEntity>> uploadFile(FileDto fileDto) {
        if (fileDto.getMultipartFiles().isEmpty()) {
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("file.not.null", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!ObjectUtils.isEmpty(fileDto.getFileSeq())) {
            fileService.deleteLegacyFile(fileDto.getFileSeq());
        }
        return fileService.fileUpload(fileDto);
    }

    @DeleteMapping("/{fileSeq}")
    public CommonResponse<String> deleteFile(@PathVariable(name = "fileSeq") String fileSeq) {
        if(fileSeq == null){
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("file.not.null", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new CommonResponse<>(fileService.deleteLegacyFile(Long.valueOf(fileSeq)));
        }
    }
}
