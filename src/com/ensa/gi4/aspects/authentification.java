package com.ensa.gi4.aspects;

import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.ensa.gi4.modele.User;


@Aspect
@Component
@EnableAspectJAutoProxy
public class authentification {
	@Around("execution( *  connecter())")
	public User trackChange(ProceedingJoinPoint jp)throws Throwable {
		 System.out.println("**************************L'authentification*******************************");
		
			User user= (User) jp.proceed();
			if (user!=null)
				return user; 
			else {
				System.out.println("le nom ou le mot de passe est incorect !");
				return null; 
		
			}
	}
}
