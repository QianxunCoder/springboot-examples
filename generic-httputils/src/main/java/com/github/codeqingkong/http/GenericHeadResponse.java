package com.github.codeqingkong.http;

import lombok.Data;

@Data
public class GenericHeadResponse<T> {
    private GenericDataResponse<T> data;
    private Integer errCode;
    private String requestId;
    private String errMsg;
    private String apiLog;
}