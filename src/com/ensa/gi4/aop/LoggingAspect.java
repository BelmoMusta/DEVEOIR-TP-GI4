package com.ensa.gi4.aop;

import com.ensa.gi4.controller.GestionMaterielController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.ensa.gi4.service.impl.GestionUserServiceImpl.getUser(..))")
    public void logAroundGetUser(ProceedingJoinPoint joinPoint) throws Throwable //,String username, String password
    {
        System.out.println("****LoggingAspect: " + joinPoint.getSignature().getName() + ": Before Method Execution");

            joinPoint.proceed();

            //Do Something useful, If you have

        System.out.println("****LoggingAspect : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
}



