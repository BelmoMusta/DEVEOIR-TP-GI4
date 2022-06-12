package com.ensa.gi4.AOP;

import com.ensa.gi4.controller.SignInController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.aop.aspectj.annotation.*;

//TODO :ASPECT


//@Aspect
//@Component
public class SignInAspect {
   /* @Autowired
    SignInController signInController;

    String joinpoint = "execution(public void signInWithUsernameAndPassword(..)";
    @Around
    public void isBlankInput(ProceedingJoinPoint joinPoint, String username, String password) throws Throwable{
        if(!username.isBlank() && !password.isBlank()){
            joinPoint.proceed();
        }else if(username.isBlank() || password.isBlank()){
            System.out.println("Données Invalides ....");
        }
    }*/
}
