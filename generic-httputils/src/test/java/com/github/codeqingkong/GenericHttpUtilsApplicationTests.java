package com.github.codeqingkong;

import com.github.codeqingkong.http.GenericHttpRequest;
import com.github.codeqingkong.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenericHttpUtilsApplicationTests {

    @Test
    void contextLoads() {
        GenericHttpRequest<Student> request = new GenericHttpRequest<>(Student.class);
        try {
            Student student = request.sendGetRequestAndParse("http://localhost:8080/rate-limit/sentinel");
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
