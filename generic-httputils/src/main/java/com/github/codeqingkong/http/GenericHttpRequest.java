package com.github.codeqingkong.http;

import com.alibaba.fastjson2.JSON;
import okhttp3.*;

import java.lang.reflect.Type;

public class GenericHttpRequest<T> {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private final Type responseType;
    private final String baseUrl;

    public GenericHttpRequest(Type responseType,String baseUrl) {
        this.responseType = responseType;
        this.baseUrl = baseUrl;
    }

    public T sendGetRequestAndParse() throws Exception {
        Request request = new Request.Builder()
                .url(this.baseUrl)
                .build();
        return executeRequest(request);
    }

    public T sendPostRequestAndParse(String postData) throws Exception {
        RequestBody requestBody = RequestBody.create(postData, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(this.baseUrl)
                .post(requestBody)
                .build();
        return executeRequest(request);
    }

    private T executeRequest(Request request) throws Exception {
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            assert response.body() != null;
            String responseBody = response.body().string();
            return JSON.parseObject(responseBody, responseType);
        }
    }
}