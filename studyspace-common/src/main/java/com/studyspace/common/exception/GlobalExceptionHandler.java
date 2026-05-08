package com.studyspace.common.exception;

import com.studyspace.common.result.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResult<Object> handleException(Exception e) {
        return ApiResult.error(e.getMessage());
    }
}