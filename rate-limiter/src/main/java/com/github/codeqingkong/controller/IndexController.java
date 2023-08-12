package com.github.codeqingkong.controller;

import com.github.codeqingkong.limit.guava.GuavaRateLimit;
import com.github.codeqingkong.limit.sentinel.SentinelRateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kongqing
 * @date 2023/08/04
 */
@RequestMapping("/index")
@RestController
public class IndexController {
    @GetMapping("/guava/rate-limit")
    @GuavaRateLimit(limitType = "/index/guava/rate-limit", limitCount = 1d)
    public String guavaRateLimit() {
        return "访问成功！";
    }

    @GetMapping("/sentinel/rate-limit")
    @SentinelRateLimit(resourceName = "sentinelRateLimit", limitCount = 1)
    public String sentinelRateLimit() {
        return "访问成功！";
    }


}