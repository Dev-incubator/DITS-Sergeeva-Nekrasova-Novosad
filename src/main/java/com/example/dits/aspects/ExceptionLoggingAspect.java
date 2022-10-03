package com.example.dits.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.dits.controllers.*.*(..))", throwing = "exception")
    public void afterException(JoinPoint joinPoint, Exception exception) {
        String message = "In " + joinPoint.getSignature().getName() + " was exception: " + exception.getClass().getName()
                + ", message: " + exception.getMessage();
        logger.info(message);
    }
}
