package com.github.codeqingkong.model;

import lombok.Data;

/**
 * @Author QingKong
 * @create 2023/8/21
 */
@Data
public class Teacher {
    private Long id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
