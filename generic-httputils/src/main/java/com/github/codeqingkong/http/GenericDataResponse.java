package com.github.codeqingkong.http;

import lombok.Data;

import java.util.List;

/**
 * @Author QingKong
 * @create 2023/8/21
 */
@Data
public class GenericDataResponse<T> {
    private Integer totalNum;
    private Integer pageSize;
    private Integer pageNum;
    private List<T> rows;
}
