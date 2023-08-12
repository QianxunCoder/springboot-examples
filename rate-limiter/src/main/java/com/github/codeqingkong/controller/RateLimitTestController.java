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
@RequestMapping("/rate-limit")
@RestController
public class RateLimitTestController {
    @GetMapping("/guava")
    @GuavaRateLimit(limitType = "rate-limit/guava", limitCount = 1d)
    public String guavaRateLimit() {
        return "访问成功！";
    }

    @GetMapping("/sentinel")
    @SentinelRateLimit(resourceName = "sentinelRateLimit", limitCount = 1)
    public String sentinelRateLimit() {
        return "访问成功！";
    }


}