package com.github.codeqingkong.springsecurity.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "/form/index";
    }
}
