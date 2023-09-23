package com.github.codeqingkong.openai;

import okhttp3.*;

public class OpenAiRequest {
    public static void main(String[] args) {
        String postData = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer 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")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(postData, MediaType.parse("application/json")))
                .build();

        OkHttpClient httpClient = new OkHttpClient();
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }
        } catch (Exception e) {

        }
    }
}