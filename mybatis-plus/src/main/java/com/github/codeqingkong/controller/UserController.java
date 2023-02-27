package com.github.codeqingkong.controller;

import com.github.codeqingkong.dto.UserDTO;
import com.github.codeqingkong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: QingKong
 * @date: 2023/2/27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public String save(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return "success";
    }
}
