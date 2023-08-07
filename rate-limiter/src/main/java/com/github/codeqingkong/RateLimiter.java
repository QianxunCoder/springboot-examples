package com.github.codeqingkong;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 * @author QingKong
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RateLimiter {
    String limitType();
    double limitCount() default 5d;
}
