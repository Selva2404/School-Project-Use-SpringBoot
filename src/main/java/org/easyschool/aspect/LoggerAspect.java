package org.easyschool.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* org.easyschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString()+"method executed start");
        Instant instant = Instant.now();
        Object returnobj = joinPoint.proceed();
        Instant finish = Instant.now();
        long duration = Duration.between(instant, finish).toMillis();
        log.info("method "+joinPoint.getSignature().toString()+" executed : "+duration+" ms");
        log.info(joinPoint.getSignature().getName()+"method execution end");
        return returnobj;
    }

    @AfterThrowing(value="execution(* org.easyschool.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint,Exception ex){
        log.error(joinPoint.getSignature()+"method executed exception : "+ex.getMessage());

    }
}
