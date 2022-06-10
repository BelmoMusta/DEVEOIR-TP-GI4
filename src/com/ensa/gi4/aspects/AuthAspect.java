package com.ensa.gi4.aspects;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.ensa.gi4.modele.User;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AuthAspect {
	
	private static Logger logger = Logger.getLogger(AuthAspect.class.getName()); 
	
	@Value("${string.authAspect}")
	private String authString; 
	
	@Value("${string.authAspect.retry}")
	private String retry; 
	
	@Value("${string.authAspect.loginSuccess}")
	private String loginSuccess; 
	
	public AuthAspect() throws  IOException {
		FileHandler fileHandler = new FileHandler("logAuth.txt", true);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
	}
	
	@Around(value = "execution (* authentification(*))")
	public Optional<User> auth(ProceedingJoinPoint jp) throws Throwable {
		

		Optional<User> checkUser = (Optional<User>) jp.proceed(); 
		if (checkUser.isPresent()) {
			logger.info(loginSuccess + checkUser.get().toString());
			return  checkUser; 	
		}
		else {
			System.out.println("----------------------------------------------------");
			System.out.println(authString);
			System.out.println(retry);
			System.out.println("----------------------------------------------------");
			return Optional.empty(); 
		}
		
	}

}
