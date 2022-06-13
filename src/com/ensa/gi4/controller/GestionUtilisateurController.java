package com.ensa.gi4.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUtilisateurService;

@Component("controllerUser")
public class GestionUtilisateurController {
	Scanner scanner = new Scanner(System.in);

	@Autowired
    @Qualifier("userService")
    private GestionUtilisateurService gestionUserService;
	
	
	public Utilisateur login() {
		return gestionUserService.login();
    }
	
	public void afficherMenu(Utilisateur user){
		if(!"admin".equals(user.getRole())){
			gestionUserService.afficherMenuEmployee();
		}
		else {
			gestionUserService.afficherMenuAdmin();

		}
					
	}
	
	public void gestionChoix(Utilisateur user) {
		 String next = scanner.next();

		if(!"admin".equals(user.getRole())) {
			gestionUserService.gestionChoixEmployee(user,next);
		}
		else {
			gestionUserService.gestionChoixAdmin(user,next);
			
			}
		 afficherMenu(user);
	}
		   	
}
