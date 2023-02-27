package com.github.codeqingkong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.codeqingkong.dto.UserDTO;
import com.github.codeqingkong.entity.UserDO;

/**
 * @author: QingKong
 * @date: 2023/2/27
 */
public interface IUserService extends IService<UserDO> {
        void save(UserDTO userDTO);
}
