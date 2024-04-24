package com.au.GenesianTheatreCompany.aspect;

import com.au.GenesianTheatreCompany.service.LoggingService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private LoggingService loggingService;

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {}

    @Before("controller() && execution(* *.*(..))")
    public void logAction(JoinPoint joinPoint) {
        // 获取方法名和类名
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        // 构建日志信息
        String logMessage = "User X executed " + className + "." + methodName;

        // 调用日志服务
        loggingService.writeLog(logMessage);
    }
}

