package com.github.codeqingkong;

import com.github.codeqingkong.http.GenericDataResponse;
import com.github.codeqingkong.http.GenericHeadResponse;
import com.github.codeqingkong.http.GenericHttpRequest;
import com.github.codeqingkong.model.Student;
import com.github.codeqingkong.model.Teacher;
import com.github.codeqingkong.util.RetryUtil;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GenericHttpUtilsApplicationTests {

    @Test
    void teacherTest() {
        Type genericHeadResponseType = new TypeToken<GenericHeadResponse<Teacher>>() {
        }.getType();
        GenericHttpRequest<GenericHeadResponse<Teacher>> request = new GenericHttpRequest<>(genericHeadResponseType, "http://localhost:8080/rate-limit/sentinel");

        try {
            GenericHeadResponse<Teacher> studentResponse = request.sendGetRequestAndParse();
            GenericDataResponse<Teacher> data = studentResponse.getData();
            List<Teacher> rows = data.getRows();
            rows.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void studentTest() {
        Type genericHeadResponseType = new TypeToken<GenericHeadResponse<Student>>() {
        }.getType();
        GenericHttpRequest<GenericHeadResponse<Student>> request = new GenericHttpRequest<>(genericHeadResponseType, "http://localhost:8080/rate-limit/sentinel");

        try {
            GenericHeadResponse<Student> studentResponse = request.sendGetRequestAndParse();
            GenericDataResponse<Student> data = studentResponse.getData();
            List<Student> rows = data.getRows();
            rows.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void retryTest() {
        RetryUtil<GenericHeadResponse<Student>> retryUtil = new RetryUtil<>(5, 5, TimeUnit.SECONDS);
        Callable<GenericHeadResponse<Student>> task = () -> {
            Type genericHeadResponseType = new TypeToken<GenericHeadResponse<Student>>() {
            }.getType();
            GenericHttpRequest<GenericHeadResponse<Student>> request = new GenericHttpRequest<>(genericHeadResponseType, "http://localhost:8080/rate-limit/sentinel");
            return request.sendGetRequestAndParse();
        };
        try {
            GenericHeadResponse<Student> genericHeadResponse = retryUtil.execute(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
