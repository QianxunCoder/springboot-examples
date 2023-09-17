package com.github.codeqingkong.limit.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.github.codeqingkong.exception.ServerException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class SentinelLimitAop {
    @Pointcut("@annotation(com.github.codeqingkong.limit.sentinel.SentinelRateLimit)")
    public void rateLimit() {
    }

    @Around("rateLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 1. 获取当前的调用方法
        Method currentMethod = getCurrentMethod(joinPoint);
        if (Objects.isNull(currentMethod)) {
            return null;
        }
        // 2. 获取注解中的值
        String resourceName = currentMethod.getAnnotation(SentinelRateLimit.class).resourceName();
        if (!StringUtils.hasText(resourceName)) {
            throw new ServerException("资源名称为空");
        }
        int limitCount = currentMethod.getAnnotation(SentinelRateLimit.class).limitCount();
        // 配置流控资源和规则
        initFlowRule(resourceName, limitCount);

        Object result = null;
        try (Entry entry = SphU.entry(resourceName)) {
            // 执行限流操作
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } catch (BlockException e) {
            // 资源被限流，这里做限流后的处理
            throw new ServerException("当前访问人数过多，请稍后再试");
        }
        return result;

    }


    private static void initFlowRule(String resourceName, int limitCount) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置受保护的资源
        rule.setResource(resourceName);
        // 设置流控规则（QPS）
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置阈值
        rule.setCount(limitCount);
        rules.add(rule);
        // 加载受保护的资源到 Sentinel
        FlowRuleManager.loadRules(rules);
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