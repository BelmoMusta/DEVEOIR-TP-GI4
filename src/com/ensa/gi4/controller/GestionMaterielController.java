package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
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
    	System.out.println(" saisir votre nom ");
        Scanner scanner  = new Scanner(System.in);
        String name = scanner.next();
        System.out.println(" saisir votre mot de passe ");
        String pw = scanner.next();
      if(  gestionPersonneService.connecter(name, pw)!= null) {
    	  if(gestionPersonneService.determinerRole().equals("admin")){
    		  
    		  
    		  
    		  //************************ADMIN***************************   		  
    		  
    			while(true) {
    	    		System.out.println("-----------------------------------");
    	    		System.out.println("pour lister le materiele saisir 1 ");
    	    		System.out.println("pour chercher un materiele saisir 2 ");
    	    		System.out.println("pour allouer un materiel saisir 3 ");
    	    		System.out.println("pour rendre un matereil saisir 4 ");
    	    		System.out.println("pour afficher le matériel alloués saisir 5 ");
    	    		System.out.println("pour ajouter un matériel  saisir 6 ");
    	    		System.out.println("pour supprimer un matériel  saisir 7 ");
    	    		System.out.println("pour modifier un matériel  saisir 8 ");
    	    		
    	    		System.out.println("-----------------------------------");

    	    		String choix = scanner.next();
    	    		String donnee1, donnee2,donnee3;
    	    		int num1, num2, num3;
    	    		if(choix.equals("1")) {
    	    			gestionMaterielService.listerMateriel();
    	    		}
    	    		else if(choix.equals("2")) {
    	        		System.out.println(" saisir id : ");
    	        		donnee1 = scanner.next();
    	    			gestionMaterielService.findMateriel(Long.parseLong(donnee1));
    	    		}else if(choix.equals("3")) {
    	    			System.out.println("saisir le code du materile à allouer ");
    	    		    donnee1 = scanner.next();
    	    			System.out.println("saisir la duree d'allocation ");
    	    			donnee2  = scanner.next();
    	    			gestionPersonneService.allouerMateriel(donnee1, donnee2);
    	    		}else if(choix.equals("4")) {
    	    			System.out.println("Saisir le code du matériel à rendre");
    	    			num1 =scanner.nextInt();
    	    			gestionPersonneService.rendreMateriel(num1);
    	    			
    	    		}else if(choix.equals("5")) {
    	    			gestionPersonneService.listerMaterielAlloue();
    	    		}else if(choix.equals("6")) {
    	    			System.out.println("pour ajouter un livre saisir 1 ");
    	    			System.out.println("pour ajouter une chaise saisir 2 ");
    	    			donnee1   = scanner.next();
    	    			if(donnee1.equals("1")) {
    	    				System.out.println("saisir le code du livre");
    	    				donnee2 = scanner.next();
    	    				Materiel materiel = new Livre();
    	    				materiel.setCode(donnee2);
    	    				materiel.setName(donnee1);
        	    			gestionMaterielService.ajouterNouveauMateriel(materiel);
    	    			}if(donnee1.equals("2")) {
    	    				System.out.println("saisir le code de la chaise");
    	    				donnee2 = scanner.next();
    	    				Materiel materiel = new Chaise();
    	    				materiel.setCode(donnee2);
    	    				materiel.setName(donnee1);
        	    			gestionMaterielService.ajouterNouveauMateriel(materiel);
    	    			}else {
    	    				System.out.println("choix invalid");

    	    			}
    	    			
    	    		}else if(choix.equals("7")) {
    	    			System.out.println("Veuillez saisir l'id du matériel à supprimer ");
    	    			num1 = scanner.nextInt();
    	    			gestionMaterielService.supprimerMateriel(num1);
    	    			
    	    		}else if(choix.equals("8")) {
    	    			System.out.println("Veuillez saisir l'id du matériel à modifier ");
    	    			num1 = scanner.nextInt();
    	    			System.out.println("Veuillez saisir le nouveau nom du matériel ");
    	    			donnee1 = scanner.next();
    	    			System.out.println("Veuillez saisir le nouveau code du matériel ");
    	    			donnee2 = scanner.next();
    	    			gestionMaterielService.modifierMateriel(num1,donnee1,donnee2);


    	    		}
    	    		
    	    		else {
    	    			System.out.println("choix invalid");
    	    		}
    	    		
    	    	}
    		  
    		  
    		  
    		  
    	  }else {
    		  
    		  //******************************employe**********************
    		  
    			while(true) {
    	    		
    	    		System.out.println("pour lister le materiele saisir 1 ");
    	    		System.out.println("pour chercher un materiele saisir 2 ");
    	    		System.out.println("pour allouer un materiel saisir 3 ");
    	    		System.out.println("pour rendre un matereil saisir 4 ");
    	    		System.out.println("pour afficher le matériel alloués saisir 5 ");
    	    		String choix = scanner.next();
    	    		if(choix.equals("1")) {
    	    			gestionMaterielService.listerMateriel();
    	    		}
    	    		else if(choix.equals("2")) {
    	        		System.out.println(" saisir id : ");
    	        		choix = scanner.next();
    	    			gestionMaterielService.findMateriel(Long.parseLong(choix));
    	    		}else if(choix.equals("3")) {
    	    			System.out.println("saisir le code du materile à allouer ");
    	    			String  code = scanner.next();
    	    			System.out.println("saisir la duree d'allocation ");
    	    			String duree = scanner.next();
    	    			gestionPersonneService.allouerMateriel(code, duree);
    	    		}else if(choix.equals("4")) {
    	    			System.out.println("Saisir le code du matériel à rendre");
    	    			int id=scanner.nextInt();
    	    			gestionPersonneService.rendreMateriel(id);
    	    			
    	    		}else if(choix.equals("5")) {
    	    			gestionPersonneService.listerMaterielAlloue();
    	    		}else {
    	    			System.out.println("choix invalid");
    	    		}
    	    		
    	    	}
    		  
    	  }
    	  
    
    	 
    	 
      }else {
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
        
       // publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
    	System.out.print("Merci pour votre visite");
        System.exit(0);
    }

}
