package com.github.codeqingkong.controller;

import com.github.codeqingkong.limit.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kongqing
 * @date 2023/08/04
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    @RateLimit(limitType = "/index", limitCount = 1d)
    public String index() {
        return "访问成功！";
    }
}