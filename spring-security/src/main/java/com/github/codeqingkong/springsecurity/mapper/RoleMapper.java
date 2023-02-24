package com.github.codeqingkong.springsecurity.mapper;

import com.github.codeqingkong.springsecurity.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: QingKong
 * @date: 2023/2/21
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据用户id获取用户当前拥有的角色
     * @param userId 用户id
     * @return 用户拥有的角色
     */
    List<RoleDO> listRolesByUserId(Integer userId);
}
