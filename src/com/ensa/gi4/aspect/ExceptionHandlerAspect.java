package com.ensa.gi4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlerAspect {

    @Around("execution(* find*(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue;
        try {
            returnValue = joinPoint.proceed();
        } catch (EmptyResultDataAccessException ex) {
            returnValue = null;
        }
        return returnValue;
    }

}
