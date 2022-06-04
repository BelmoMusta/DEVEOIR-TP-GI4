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
public class AuthAspect {
	
	@Around(value = "execution (* authentification(*))")
	public User auth(ProceedingJoinPoint jp) throws Throwable {
	
		System.out.println("Traitement d'authentification ! ");
		User user= (User) jp.proceed(); 
		// gestion du cas utilisateur n'existant pas dans la bd à faire plus tard 
		if (Optional.of(user).isPresent())
			return user; 
		else {
			System.out.println("Desole l'utilisateur saisi n'existe pas dans la base de donnée !");
			return null; 
		}
		
	}

}
