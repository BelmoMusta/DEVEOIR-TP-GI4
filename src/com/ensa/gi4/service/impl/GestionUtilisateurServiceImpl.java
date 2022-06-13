package com.ensa.gi4.service.impl;
import java.util.Locale;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UserDao; 
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUtilisateurService;


@Component("userService")
public class GestionUtilisateurServiceImpl implements GestionUtilisateurService {
	 		
	Scanner scanner = new Scanner(System.in);

	@Autowired
    UserDao userDao;

	@Autowired 
	GestionMaterielService matService;
	
	
	@Autowired
    private MessageSource messageSource;
	 
	@Override
	public Utilisateur login() {
		
		System.out.println(messageSource.getMessage("utilisateur",null, Locale.FRANCE));	
        String username = scanner.next();
        System.out.println(messageSource.getMessage("mdp",null, Locale.FRANCE));
        String password = scanner.next();
         Utilisateur user=userDao.findUser(username,password);
        if(user==null) {
        	System.out.println(messageSource.getMessage("errone",null, Locale.FRANCE));
        	System.out.println(messageSource.getMessage("ressaisirOuQuitter",null, Locale.FRANCE));
        	String next=scanner.next();
        	if("1".equals(next)){
        		return login();
        	}
        	else {
        		sortirDeLApplication();
        	}
        }
        else{
           	System.out.println(messageSource.getMessage("bienvenu",null, Locale.FRANCE));
           	}  
        return user;
        }

	

	@Override
    public void sortirDeLApplication() {
        System.out.println(messageSource.getMessage("aurevoir",null, Locale.FRANCE));

        System.exit(0);
    }

    @Override
    public void afficherMenuEmployee() {
    	 System.out.println(messageSource.getMessage("sortir",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("chercher",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("allouer",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("rendre",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("afficherAllouesParMoi",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("liste",null, Locale.FRANCE));		
	}


    @Override
	public void afficherMenuAdmin() {
    	afficherMenuEmployee();
		 System.out.println(messageSource.getMessage("creer",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("modifier",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("supprimer",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("marqueindisponible",null, Locale.FRANCE));
         System.out.println(messageSource.getMessage("afficherAlloueParUtilisateur",null, Locale.FRANCE));   	
	}
   
    @Override
	 public void gestionChoixEmployee(Utilisateur user, String next) {
		 switch(next) {
		 case "0":
			 sortirDeLApplication();
			 break;
		 case "1":
			 matService.chercherMateriel();
			 break;	
		 case "2":
	        matService.allouerMateriel(user.getUserId());
			 break;
		 case "3":
	        matService.rendreMateriel(user.getUserId());
			 break;	
		 case "4":
	        matService.afficherMaterielsAllouesParMoi(user.getUserId());
			 break;
		 case "5":
	        matService.listerMateriel();
			 break;	
		 default:
			 System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
		 }
	}
	
    @Override
	 public void gestionChoixAdmin(Utilisateur user, String next) {
		 
		 switch(next) {
		 case "6":
			 matService.ajouterNouveauMateriel();		
			 break;
		 case "7":
			 matService.modifierMateriel();
			 break;	
		 case "8":
			 matService.supprimerMateriel();
			 break;
		 case "9":
			 matService.marquerIndisponible();
			 break;	
		 case "10":
			 matService.afficherMaterielsAllouesParUtilisateur();
			 break;
		 default:
			 gestionChoixEmployee(user,next);
		 }
	}
	 


	
}
