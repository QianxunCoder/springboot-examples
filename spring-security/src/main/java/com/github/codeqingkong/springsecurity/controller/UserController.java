package com.github.codeqingkong.springsecurity.controller;

import com.github.codeqingkong.springsecurity.bo.UserBO;
import com.github.codeqingkong.springsecurity.dto.UserDTO;
import com.github.codeqingkong.springsecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author QingKong
 * @date 2023/2/24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    @ResponseBody
    public String saveUser(@RequestBody UserDTO userDTO){
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userDTO,userBO);
        return userService.save(userBO) ? "success" : "fail";
    }
}
