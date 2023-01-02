package com.github.codeqingkong.springsecurity.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Controller
@RequestMapping("/form")
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "form/login";
    }
}
