package com.ensa.gi4.aop;

import java.util.Scanner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.service.api.GestionPersonneService;

@Aspect
@Component
@EnableAspectJAutoProxy
public class Connection {
	@Autowired
	GestionPersonneService gestionPersonneService;
	@Autowired 
	@Qualifier("controllerPricipal")
	GestionMaterielController gestionMaterielController;
	@Around("execution(void afficherMenu())")
	public void gererConnection(ProceedingJoinPoint jp)throws Throwable {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------------bonjour----------------");
		System.out.println(" saisir votre nom ");
		String donnee1 = scanner.next();
		System.out.println(" saisir votre mot de passe ");
		String donnee2 = scanner.next();		
		if(gestionPersonneService.connecter(donnee1, donnee2)== null) {
			System.out.println("les données sont erronnées");
			System.out.println(" saisir 0 pour sortir de l'application");
			System.out.println(" saisir 1 pour créer un compte ");
			System.out.println(" saisir n'import quel caractère pour réesayer à nouveau");

			String choix = scanner.next();
			if (choix.equals("0")) {
				sortirDeLApplication();
			} else if (choix.equals("1")) {
				gestionMaterielController.creerCompteEmploye();
				gererConnection(jp);
			} else {
				
				gererConnection(jp);
			}
			
		}else {
			jp.proceed();
		}
	}
	
	public void sortirDeLApplication() {
		System.out.print("Merci pour votre visite");
		System.exit(0);
	}

}
