package com.ensa.gi4.service.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionUtilisateurService;

import junit.framework.TestCase;

public class GestionUtilisateurServiceTest extends TestCase {

	@Autowired
	GestionUtilisateurService userService;
	
	Utilisateur utilisateurTrue; //Un utilisateur reel
	Utilisateur utilisateurFake; //Un utilisateur imaginaire
	Utilisateur utilisateurCheckTrue; //Un utilisateur de test
	Utilisateur utilisateurCheckFalse; //Un utilisateur de test
	
	boolean is_authentified = true;
	boolean not_authentified = true;
	
	//Initialisation des ressources
	public void setUp() {
		 
		utilisateurTrue.setUsername("airbus");
		utilisateurTrue.setPassword("380");
		
		
		utilisateurFake.setUsername("airbus");
		utilisateurFake.setPassword("350");
		
		utilisateurCheckTrue = new Utilisateur();
		utilisateurCheckFalse = new Utilisateur();
	}
	
	//La méthode à tester
	public void testLoginS() {
		
		utilisateurCheckTrue = userService.loginS(utilisateurTrue);
		utilisateurCheckFalse = userService.loginS(utilisateurFake);
		
		if(utilisateurCheckTrue == null) {
			
			is_authentified = false;
		}
		
		if(utilisateurCheckFalse != null) {
			
			not_authentified = false;
		}
		
		assertEquals("Le retour du premier test de la méthode d'authentification", is_authentified, true);
		assertEquals("Le retour du deuxième test de la méthode d'authentification", not_authentified, true);
		
	}
	
	
	//Libération des ressources
	public void tearDown() {
		
		utilisateurTrue = null;
		utilisateurFake = null;
		utilisateurCheckTrue = null;
		utilisateurCheckFalse = null;
	}
	
}
