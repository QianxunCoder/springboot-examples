package com.github.codeqingkong.limit.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author QingKong
 * @create 2023/8/9
 */
public class RateLimitHelper {
    private static Map<String,RateLimiter> rateLimiterMap = new HashMap<>();
    public static RateLimiter getRateLimiter(String key, Double limitCount) {
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(limitCount);
            rateLimiterMap.put(key, rateLimiter);
        }
        return rateLimiter;
    }
}
