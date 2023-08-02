package com.github.codeqingkong.springsecurity.entity;

import lombok.Data;

/**
 * @author: QingKong
 * @date: 2023/2/21
 */
@Data
public class RoleDO {
    private Integer id;
    private String name;
    private String nameZh;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"nameZh\":\"")
                .append(nameZh).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
