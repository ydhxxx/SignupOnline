package com.example.signuponline.aspect;


import com.example.signuponline.common.LogEntity;
import com.example.signuponline.util.IpInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ...
 *
 * @author yudh
 * @date 2020-12-23 15:44:58
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Pointcut("execution(public * com.example.signuponline.controller.WeChatController..*(..))")
    public void log() {}

    /**
     *
     * @AfterReturning  最终
     */
    @AfterReturning("log()")
    public void log(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LogEntity logEntity = new LogEntity();
//        logEntity.setUserId(SessionUtil.getUserId(request.getSession()));
        logEntity.setIp(IpInfoUtil.getIpAddr(request));
        logEntity.setUrl(request.getRequestURI());
        logEntity.setType(request.getMethod());
        logEntity.setArgs(joinPoint.getArgs());
        Signature signature = joinPoint.getSignature();
        logEntity.setMethod(signature.getDeclaringTypeName() + "." + signature.getName() + "()");
        log.info(logEntity.toString());
    }
}
