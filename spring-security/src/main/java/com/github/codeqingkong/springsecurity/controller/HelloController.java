package com.github.codeqingkong.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }
}
