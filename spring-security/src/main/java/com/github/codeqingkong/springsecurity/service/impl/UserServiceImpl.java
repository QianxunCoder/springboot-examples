package com.github.codeqingkong.springsecurity.service.impl;

import com.github.codeqingkong.springsecurity.entity.User;
import com.github.codeqingkong.springsecurity.mapper.RoleMapper;
import com.github.codeqingkong.springsecurity.mapper.UserMapper;
import com.github.codeqingkong.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        User user = userMapper.loadUserByUsername(username);
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        user.setRoles(roleMapper.listRolesByUserId(user.getId()));
        LOGGER.info("loadUserByUsername method exit param: {}",user);
        return user;
    }
}
