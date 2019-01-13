package com.epam.training.sportsbeatting.aspect;

import java.util.Arrays;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceExecutionDetailsAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around("serviceLayerMethods()")
    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        final Object methodResult = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;

        logger.info("Method {} took {} ms.", joinPoint.getSignature().toString(), timeTaken);
        return methodResult;
    }

    @Before("serviceLayerMethods()")
    private void logMethodNameAndArgs(JoinPoint joinPoint) {
        logger.info("Method {} called with parameters {}.", joinPoint.getSignature().toString(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "serviceLayerMethods()", returning = "result")
    private void logMethodResult(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned result {}.", joinPoint.getSignature().toString(),
                Objects.isNull(result) ? "null" : result.toString());
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void serviceLayerMethods() {}

}
