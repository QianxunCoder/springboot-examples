package com.github.codeqingkong.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: QingKong
 * @date: 2023/2/27
 */
@TableName(value = "user")
public class UserDO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String json;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"json\":\"")
                .append(json).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
