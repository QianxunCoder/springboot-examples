package com.github.codeqingkong.util;

import com.github.rholder.retry.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 重试工具类
 *
 * @Author QingKong
 * @create 2023/8/23
 */
public class RetryUtil<T> {
    private final Retryer<T> retryer;

    public RetryUtil(int maxAttempts, long sleepTime, TimeUnit timeUnit) {
        this.retryer = RetryerBuilder.<T>newBuilder()
                .retryIfException()
                .withWaitStrategy(WaitStrategies.fixedWait(sleepTime, timeUnit))
                .withStopStrategy(StopStrategies.stopAfterAttempt(maxAttempts))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        System.out.println("第" + attempt.getAttemptNumber() + "次重试");
                    }
                })
                .build();
    }

    public T execute(Callable<T> task) throws Exception {
        return retryer.call(task);
    }
}
