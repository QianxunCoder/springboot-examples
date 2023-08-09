package com.github.codeqingkong.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServerException.class)
    public String serviceException(ServerException e) {
        return e.getMessage();
    }
}