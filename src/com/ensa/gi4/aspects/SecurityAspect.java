package com.ensa.gi4.aspects;

import com.ensa.gi4.controller.UserController;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ensa.gi4.utils.hasAuthority;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityAspect {
    private final UserController userController;
    private final UserService userService;
    private final I18nService i18nService;

    @Before("execution( void com..GestionMaterielController.*(..) ) || execution( void com..GestionMaterielServiceImpl.*(..))")
    public void checkSessionNotExpired(){
        if (userService.isUserExpired()){
            userController.confirmUser();
        }else{
            userService.refreshValidity();
        }
    }

    @Around("@annotation(com.ensa.gi4.utils.hasAuthority)")
    public void needAdminAuthority(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        String authority = signature.getMethod().getAnnotation(hasAuthority.class).value();
        if(!userService.hasRole(authority)){
            System.out.println(i18nService.getText("message.info.forbidden"));
        }else{
            jp.proceed();
        }
    }
}
