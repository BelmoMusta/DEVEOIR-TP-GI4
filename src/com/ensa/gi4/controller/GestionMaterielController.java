package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

	@Autowired

    ApplicationPublisher publisher;
	 @Autowired 
	 GestionPersonneService gestionPersonneService;
	 @Autowired
	 @Qualifier("materielService")
	 GestionMaterielService gestionMaterielService;

	 public void afficherMenu() {
		 
	    	System.out.println("----------------bonjour----------------");
	    	System.out.println("pour se connecter saisir 1 ");
    		System.out.println("pour créer un nouveau compte saisir 2 ");
    		Scanner scanner  = new Scanner(System.in);
    		String Log = scanner.next();
    		if(Log.equals("1")) {
    			System.out.println(" saisir votre nom ");
    	        String name = scanner.next();
    	        System.out.println(" saisir votre mot de passe ");
    	        String pw = scanner.next();
    	       if( gestionPersonneService.connecter(name, pw)!= null) {
    	    	  
    	    	while(true) {
    	    		if(gestionPersonneService.isAdmin(name, pw))
    	    		{
    	    			System.out.println("---------------------------------------------------------------");
    	    			System.out.println("pour Afficher la liste de tous les matériels saisir 1 ");
        	    		System.out.println("pour chercher un materiel saisir 2 ");
        	    		System.out.println("pour  Créer un nouveau matériel saisir 3 ");
        	    		System.out.println("pour Supprimer un matériel saisir 4 ");
        	    		System.out.println("pour Modifier les informations d'un matériel saisir 5 ");
        	    		System.out.println("pour Marquer un matériel indisponible saisir 6 ");
        	    		System.out.println("pour Allouer un matériel saisir 7 ");
        	    		System.out.println("pour rendre un materiele saisir 8 ");
        	    		System.out.println("pour Afficher la liste des matériels alloués  saisir 9 ");
        	    		System.out.println("pour Afficher la liste des matériels alloués par chaque utilisateur saisir 10 ");
        	    		
        	    		System.out.println("---------------------------------------------------------------");
  
        	    		String choix = scanner.next();
        	    		if(choix.equals("1")) {
        	    			gestionMaterielService.listerMateriel();
        	    		}
        	    		else if(choix.equals("2")) {
        	        		System.out.println(" saisir id : ");
        	        		choix = scanner.next();
        	    			gestionMaterielService.findMateriel(Long.parseLong(choix));
        	    		}   
        	    		else if (choix.equals("3")) {
        	    		  Materiel M= new Materiel() { // because it is abstract
        	    		        };
        	    			System.out.println(" saisir le nom du materiel  : ");
        	    			String m1 = scanner.next();
        	    			System.out.println(" saisir le code de materiel : ");
        	    			String m2 = scanner.next();
        	    			M.setName(m1);
        	    			M.setCode(m2);
        	    			gestionMaterielService.ajouterNouveauMateriel(M);
        	    		}
        	    		else if  (choix.equals("4")) {
        	    			System.out.println(" saisir L'id du materiel  : ");
        	    			Long id = Long.parseLong(scanner.next());
        	    			gestionMaterielService.supprimerMateriel(id);
        	    		}
        	    		else if (choix.equals("5")) {
        	    			System.out.println(" saisir L'id du materiel que vous souhaitez modifier : ");
        	        		Long id =  Long.parseLong( scanner.next());
      	    			System.out.println(" saisir le nouveau nom du materiel  : ");
      	    			String nvName = scanner.next();
      	    			System.out.println(" saisir le nouveau code de materiel : ");
      	    			String nvCode = scanner.next();
      	    			gestionMaterielService.modifierInfosMateriel(id, nvName, nvCode);
        	    		}
    	    			
        	    		else if (choix.equals("6")) 
        	    		{
        	    			System.out.println(" saisir L'id du materiel que vous souhaitez modifier : ");
        	        		Long id =  Long.parseLong( scanner.next());
        	        		gestionMaterielService.indisponibleMateriel(id);
        	    			
        	    		}
        	    		else if (choix.equals("7")) {
        	    			System.out.println("saisir le nom du materile à allouer : ");
        	    		   String m1 = scanner.next();
        	    			System.out.println("saisir la duree d'allocation (par jour): ");
        	    			String m2 = scanner.next();
        	    			gestionPersonneService.allouerMateriel(m1,m2);
        	    		}
        	    		else if (choix.equals("8")) 
        	    		{
        	    			
        	    		}
        	    		else if (choix.equals("9")) 
        	    		{
        	    			
        	    		}
        	    		
        	    		
        	    		
        	    		
        	    		
        	    		
        	    		
    	    		}else {
    	    			System.out.println("---------------------------------------------------------------");
    	    			
    	    			System.out.println("pour afficher la liste de tous les matériels saisir 1 ");        	    		
        	    		System.out.println("pour  Chercher un matériel saisir 2 ");
        	    		System.out.println("pour afficher la liste des matériels alloués saisir 3");
        	    		System.out.println("pour allouer un matériel saisir 4 ");
        	    		System.out.println("pour  rendre un matériel saisir 5 ");
        	    		
        	    		System.out.println("---------------------------------------------------------------");
        	    		
        	    		String choix = scanner.next();
        	    		if(choix.equals("1")) {
        	    			gestionMaterielService.listerMateriel();
        	    		}
        	    		else if(choix.equals("2")) {
        	        		System.out.println(" saisir id : ");
        	        		choix = scanner.next();
        	    			gestionMaterielService.findMateriel(Long.parseLong(choix));
        	    		}
    	    			
    	    		}
    	    	 	    		
    	    	}
    	    	     	    	 
    	      }else {
    	    	  System.out.println("données errounées!!");
    	    	  System.out.println(" saisir 0 pour sortir de l'application");
    	    	  System.out.println(" saisir 1 pour réesayer à nouveau");
    	    	  String choix = scanner.next();
    	    	  if(choix.equals("0")) {
    	    		  sortirDeLApplication();
    	    	  }else {
    	    		  name=null;
    	    		  pw=null;
    	    		 afficherMenu();
    	    	  }
    	      }
    		}
    		else if(Log.equals("2")) {
    			System.out.println("veuillez entrer votre nom");
    			String nvName = scanner.next();
    	        System.out.println(" saisir votre mot de passe ");
    	        String nvPw = scanner.next();
    	        gestionPersonneService.creerCompte(nvName, nvPw);
    	        
    			
    		}
    		else {
    			System.out.println("Votre Choix invalide !!");
    		}
	       // publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
	    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
