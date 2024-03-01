package com.jaypark8282.core.exception.handler;

import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.resonse.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CommonResponse<?>> handleCustomException(CustomException exception) {
        CommonResponse<?> commonResponse = new CommonResponse<>(exception.getErrorCode(), exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(commonResponse);
    }
}
