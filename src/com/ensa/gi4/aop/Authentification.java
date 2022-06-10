package com.ensa.gi4.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.service.api.GestionUserService;

@Aspect
@Component
@EnableAspectJAutoProxy
public class Authentification {
	@Autowired
	 GestionUserService gestionUser;
	@Autowired 
	@Qualifier("controllerPricipal")
	GestionMaterielController gestionMaterielController;
	
	/*@Before("execution(User connexion(..))")
	//public void gestion(){
		//System.out.println("gestion de connexion");
	} */

}
