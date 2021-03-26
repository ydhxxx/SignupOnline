package com.example.signuponline.aspect;

import com.alibaba.fastjson.JSON;
import com.example.signuponline.aspect.annotation.AppLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2020-12-23 15:54:31
 */
@Component
@Aspect
@Slf4j
public class AppLogAspect {

    @Pointcut(value = "@annotation(com.example.signuponline.aspect.annotation.AppLog)")
    public void appLog() {}

    @Around("appLog()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        try {
            log(joinPoint, result);
        } catch (Exception e) {
            log.error("记录APP日志发生错误：", e);
        }
        return result;
    }

    private void log(ProceedingJoinPoint joinPoint, Object result) throws NoSuchMethodException, SecurityException {
        Map<String, String> logInfo = new HashMap<>();
        logInfo.put("request", JSON.toJSONString(joinPoint.getArgs()));
        logInfo.put("response", result.toString());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
        AppLog asLog = method.getAnnotation(AppLog.class);
        if (StringUtils.isNotBlank(asLog.value())) {
            logInfo.put("note", asLog.value());
        } else {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation != null && StringUtils.isNotBlank(apiOperation.value())) {
                logInfo.put("note", apiOperation.value());
            }
        }
        log.info(JSON.toJSONString(logInfo));
    }
}

