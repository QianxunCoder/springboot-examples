package com.github.codeqingkong.limit;

import com.github.codeqingkong.exception.ServerException;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author QingKong
 * @create 2023/8/9
 */
@Aspect
@Component
public class GuavaLimitAop {
    @Before("execution(@com.github.codeqingkong.limit.RateLimit * *(..))")
    public void limit(JoinPoint joinPoint) {
        // 1. 获取当前的调用方法
        Method currentMethod = getCurrentMethod(joinPoint);
        if (Objects.isNull(currentMethod)) {
            return;
        }
        // 2. 从方法注解上获取限流的配置
        double limitCount = currentMethod.getAnnotation(RateLimit.class).limitCount();
        String limitType = currentMethod.getAnnotation(RateLimit.class).limitType();
        // 使用guava的令牌桶算法获取一个令牌，获取不到则等待
        RateLimiter rateLimiter = RateLimitHelper.getRateLimiter(limitType, limitCount);
        boolean acquire = rateLimiter.tryAcquire();
        // 3. 如果获取不到令牌，进行限流操作
        if (!acquire) {
            throw new ServerException("当前访问人数过多，请稍后再试");
        }
    }

    private Method getCurrentMethod(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method targetMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                targetMethod = method;
                break;
            }
        }
        return targetMethod;
    }
}
