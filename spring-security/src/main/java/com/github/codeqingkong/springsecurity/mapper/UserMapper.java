package com.github.codeqingkong.springsecurity.mapper;

import com.github.codeqingkong.springsecurity.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: QingKong
 * @date: 2023/2/21
 */
@Mapper
public interface UserMapper {
    /**
     * 根据 username 获取用户
     * @param name
     * @return
     */
    User loadUserByUsername(String name);
}
