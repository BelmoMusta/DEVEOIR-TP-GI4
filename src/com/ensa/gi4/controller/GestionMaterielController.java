package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

//    @Autowired
//
//    ApplicationPublisher publisher;
//
//    public void afficherMenu() {
//        Scanner scanner  = new Scanner(System.in);
//        //publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
//    }
//
//    private void sortirDeLApplication() {
//        System.exit(0);
//    }
	
	@Autowired
	GestionUserService gestionUserService ;
	@Autowired
	GestionMaterielService gestionMaterielServiceImpl;
	 Scanner scanner = new Scanner(System.in);
	 public void afficherMenu() {
		 
		 System.out.println("entrer votre nom");
	      String name= scanner.next();
	      System.out.println("entrer votre mot de passe");
	      String password = scanner.next();
	      User userActuelle= gestionUserService.findUser(name,password);
	      if(userActuelle==null) 
	      {
	    	  System.out.println("le nom ou le mot de passe est incorect");
	      }
	      
	      else {
	    	  String next;
	    	  afficherMenuUser();
	    	  if(userAdmin(userActuelle)) {
	    		  afficherMenuAdmin();
	    		  }
	    	  next = scanner.next();
	    	  if ("0".equals(next)) {
		             // sortirDeLApplication();
		          }
		         else if ("1".equals(next)) {
		        	 listerMateriel();
	  	            
	  	        } else if ("2".equals(next)) {
	  	        	chercherMateriel();
	  	        }
	  	        else if ("3".equals(next)) {
	  	        	
	  	        	allouerMateriel(userActuelle);
	  	        }
	  	        else if ("4".equals(next)) {
	  	        	
	  	          System.out.println("10- Chercher un matériel, entrer 4");
	  	        }
	  	      else if ("5".equals(next)) {
	  	    	  System.out.println("10- Chercher un matériel, entrer 5");
	  	        	
	  	        }
	  	      else if(userAdmin(userActuelle)) {
	    		  //afficherMenuAdmin();
	    		//  next = scanner.next();
	    		  if ("6".equals(next)) {
	    			  System.out.println("10- Chercher un matériel, entrer 6");
	    	        } else if ("7".equals(next)) {
	    	        	  System.out.println("10- Chercher un matériel, entrer 7");
	    	        } else if ("8".equals(next)) {
	    	        	  System.out.println("10- Chercher un matériel, entrer 8");
	    	        }
	    	        else if ("9".equals(next)) {
	    	        	  System.out.println("10- Chercher un matériel, entrer 9");
	    	        	
	    	        }
	    	        else if ("10".equals(next)) {
	    	        	  System.out.println("10- Chercher un matériel, entrer 10");
	    	        	
	    	        }
	    	        else {System.out.println("choix invalide");}
	    		   }
  	        else {System.out.println("choix invalide");}
	         
	      }
	     
   }
	 public void afficherMenuUser() {
		 System.out.println("0- pour sortir de l'application, entrer 0");
		 System.out.println("1- pour Afficher la liste de tous les matériels, entrer 1");
         System.out.println("2- pour Afficher la liste des matériels alloués par lui même, entrer 2");
         System.out.println("3- pour Rendre un matériel, entrer 3");
         System.out.println("4- pour Allouer un matériel, entrer 4");
         System.out.println("5- Chercher un matériel, entrer 5");
        
	 }
	 public void afficherMenuAdmin() {
		 System.out.println("6- pour Afficher la liste de tous les matériels, entrer 1");
         System.out.println("7- pour Afficher la liste des matériels alloués par lui même, entrer 2");
         System.out.println("8- pour Rendre un matériel, entrer 3");
         System.out.println("9- pour Allouer un matériel, entrer 4");
         System.out.println("10- Chercher un matériel, entrer 5");
        
	 }
	 private boolean userAdmin(User user) {
		return gestionUserService.userAdmin(user);
	 }
	 
	  private void sortirDeLApplication() {
	        System.exit(0);
	    }
	  private void listerMateriel() {
		  System.out.println("voici la liste de tous les matériels");
		  gestionMaterielServiceImpl.listerMateriel();
	  }
	  public void chercherMateriel() {
		  System.out.println("Veuillez saisir l'id du materiel");
		  Long id= scanner.nextLong();
	    	 gestionMaterielServiceImpl.chercherMateriel(id);
	    }
	  public void allouerMateriel(User user) {
		  System.out.println("Veuillez saisir le nom du materiel");
		  String name= scanner.next();
	    	 gestionMaterielServiceImpl.alloue(name,user.getId());
	  }

}
