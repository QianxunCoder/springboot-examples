package com.github.codeqingkong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.codeqingkong.dto.UserDTO;
import com.github.codeqingkong.entity.UserDO;
import com.github.codeqingkong.mapper.UserMapper;
import com.github.codeqingkong.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author: QingKong
 * @date: 2023/2/27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Override
    public void save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        baseMapper.insert(userDO);
    }
}
