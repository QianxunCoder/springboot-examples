package com.github.codeqingkong.limit.sentinel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SentinelRateLimit {
    String resourceName();
    int limitCount() default 5;
}
