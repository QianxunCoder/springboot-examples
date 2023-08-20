package com.github.codeqingkong.model;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);

        sb.append(",\"name\":\"")
                .append(name).append('\"');

        sb.append(",\"age\":")
                .append(age);

        sb.append('}');
        return sb.toString();
    }
}