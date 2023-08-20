package com.github.codeqingkong.http;

import lombok.Data;

@Data
public class GenericResponse<T> {
    private T data;
}