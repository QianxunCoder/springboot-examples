package com.github.codeqingkong.springsecurity.service.impl;

import com.github.codeqingkong.springsecurity.bo.UserBO;
import com.github.codeqingkong.springsecurity.entity.UserDO;
import com.github.codeqingkong.springsecurity.mapper.RoleMapper;
import com.github.codeqingkong.springsecurity.mapper.UserMapper;
import com.github.codeqingkong.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

/**
 * @author: QingKong
 * @date: 2023/2/21
 */
@Service

public class UserServiceImpl implements UserService, UserDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername method enter param: {}",username);
        UserDO userDO = userMapper.loadUserByUsername(username);
        if (userDO == null){
            throw new RuntimeException("用户不存在");
        }
        userDO.setRoles(roleMapper.listRolesByUserId(userDO.getId()));
        LOGGER.info("loadUserByUsername method exit param: {}", userDO);
        return userDO;
    }

    @Override
    public boolean save(UserBO userBO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userBO,userDO);
        // 密码加密
        userDO.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userDO.getPassword()));
        userDO.setEnabled(true);
        userDO.setAccountNonLocked(true);
        userDO.setAccountNonExpired(true);
        userDO.setCredentialsNonExpired(true);
        userMapper.insertUser(userDO);
        return false;
    }
}
