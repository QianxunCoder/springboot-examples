package com.github.codeqingkong.http;

import com.alibaba.fastjson2.JSON;
import okhttp3.*;

public class GenericHttpRequest<T> {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private final Class<T> responseType;

    public GenericHttpRequest(Class<T> responseType) {
        this.responseType = responseType;
    }

    public T sendGetRequestAndParse(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return executeRequest(request);
    }

    public T sendPostRequestAndParse(String url, String postData) throws Exception {
        RequestBody requestBody = RequestBody.create(postData, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
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
            String responseBody = JSON.parseObject(response.body().string()).getJSONObject("data").toJSONString();
            return JSON.parseObject(responseBody, responseType);
        }
    }
}