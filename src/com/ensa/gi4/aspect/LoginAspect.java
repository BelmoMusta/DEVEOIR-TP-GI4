package com.ensa.gi4.aspect;


import com.ensa.gi4.controller.GestionMaterielController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {


    @Around("execution(public boolean login(..)) && args(username,password)")
    public void checkEmptyLoginFields(ProceedingJoinPoint joinPoint, String username, String password)throws Throwable{
        if(username.isBlank() || password.isBlank()){
            joinPoint.proceed();
        }else if (!username.isBlank() && !password.isBlank()){
            System.out.println("Les champs sont vides!! Veuillez réessayer");
        }
    }
}
