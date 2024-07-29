package com.jaypark8282.core.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FileDto {
    List<MultipartFile> multipartFiles;
}
