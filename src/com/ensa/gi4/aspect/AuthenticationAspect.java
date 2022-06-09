package com.ensa.gi4.aspect;

import com.ensa.gi4.controller.AuthenticationController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    AuthenticationController authenticationController;

    @Around("execution(public void authenticateWithUsernameAndPassword(..)) && args(username, password)")
    public void checkForEmptyUsernameOrPassword(ProceedingJoinPoint joinPoint, String username, String password) throws Throwable {
        if (!username.isBlank() && !password.isBlank()) {
            joinPoint.proceed();
        } else if (username.isBlank() || password.isBlank()) {
            System.out.println("veuillez saisir des informations valides");
        }
    }
}
