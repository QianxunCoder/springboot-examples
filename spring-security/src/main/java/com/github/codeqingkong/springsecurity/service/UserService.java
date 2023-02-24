package com.github.codeqingkong.springsecurity.service;

import com.github.codeqingkong.springsecurity.bo.UserBO;

/**
 * @author: LoganLuo
 * @date: 2023/2/21
 */
public interface UserService {
    /**
     * 添加一个新用户
     * @param userBO
     * @return
     */
    boolean save(UserBO userBO);
}
