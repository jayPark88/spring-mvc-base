package com.jaypark8282.base.api.v1.file.service;

import com.jaypark8282.core.jpa.entity.FileEntity;
import com.jaypark8282.core.jpa.repository.FileRepository;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

//    private final FileRepository fileRepository;

    public CommonResponse<FileEntity> fileUpload(){
        return null;
    }
}
