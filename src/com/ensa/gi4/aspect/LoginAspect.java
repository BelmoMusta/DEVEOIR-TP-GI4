package com.ensa.gi4.aspect;

import java.util.Scanner;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.UserService;


@Aspect
@Component
public class LoginAspect {
	

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	public void findUser(User user) {
		userService.findUser(user);
	}
	
	@Pointcut("execution(* com.ensa.gi4.controller.GestionMaterielController.AfficherMenu(..))")
	public void loggin() {}
	
	@Before("loggin()")
	public void login() {
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.println("Entrer le nom :");
		String username = sc.nextLine();
		System.out.println("Entrer le mot de passe :");
		String password = sc.nextLine();
		user.setUsername(username);
		user.setPassword(password);
		findUser(user);
		
	}
}
