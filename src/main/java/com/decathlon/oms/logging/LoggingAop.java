package com.ge.grid.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.ge.grid.util.Constants.TeamplateConstants.ENTER_LOG;
import static com.ge.grid.util.Constants.TeamplateConstants.EXECUTION_LOG;


@Aspect
@Component
public class LoggingAop {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(String.format(EXECUTION_LOG,joinPoint.getSignature(),executionTime));
        return proceed;
    }

    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        System.out.println(String.format(ENTER_LOG,joinPoint.getSignature(),joinPoint.getArgs()[0].toString()));
        return proceed;
    }

}
