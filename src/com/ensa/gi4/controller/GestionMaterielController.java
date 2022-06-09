package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
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

	 public User connecter() {
		 System.out.println("entrer votre nom");
	      String name= scanner.next();
	      System.out.println("entrer votre mot de passe");
	      String password = scanner.next();
	  
		 return gestionUserService.findUser(name,password);
	 }
		
	 public void afficherMenuPrincipale(User userActuelle) {
		
		  String next;
    	  afficherMenuUser();
    	  if(userAdmin(userActuelle)) {
    		  afficherMenuAdmin();
    		  }
    	  next = scanner.next();
    	  if ("0".equals(next)) {
	              sortirDeLApplication();
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
  	        	
  	         ListerMesAllocations(userActuelle);
  	        }
  	      else if ("5".equals(next)) {
  	    	 rendreMateriel(userActuelle.getId());
  	        	
  	        }
  	      else if(userAdmin(userActuelle)) {
    		  //afficherMenuAdmin();
    		//  next = scanner.next();
    		  if ("6".equals(next)) {
    			 creerMateriel();
    	        } else if ("7".equals(next)) {
    	        	 supprimerMateriel();
    	        } else if ("8".equals(next)) {
    	        	 modifierMateriel();
    	        }
    	        else if ("9".equals(next)) {
    	        	  indisponibleMateriel();
    	        	
    	        }
    	        else if ("10".equals(next)) {
    	        	 listerMaterielAlloue();
    	        	
    	        }
    	        else {System.out.println("choix invalide");}
    		   }
	        else {System.out.println("choix invalide");}
    	  System.out.println("*********************************************************");
 		 System.out.println("pour afficher le menu entrer 1");
 		  next = scanner.next();
 		  if ("1".equals(next)) {
 			 afficherMenuPrincipale(userActuelle) ;
          }
         else  {
        	 sortirDeLApplication();
	            
	        }
 		
	 }
	 
	 
	 public void afficherMenuUser() {
		 System.out.println("*************************le menu********************************");
		 System.out.println("0- pour sortir de l'application, entrer 0");
		 System.out.println("1- pour Afficher la liste de tous les matériels, entrer 1");
         System.out.println("2- pour Chercher un matériel, entrer 2");
         System.out.println("3- pour Allouer un matériel");
         System.out.println("4- pour Afficher la liste des matériels alloués par lui même, entrer 4");
         System.out.println("5- pour Rendre un matériel, entrer 5");
        
	 }
	 public void afficherMenuAdmin() {
		 System.out.println("6- pour Créer un nouveau matériel, entrer 6");
         System.out.println("7- pour Supprimer un matériel, entrer 7");
         System.out.println("8- pour Modifier les informations d'un matériel, entrer 8");
         System.out.println("9- pour Marquer un matériel indisponible, entrer 9");
         System.out.println("10- pour Afficher la liste des matériels alloués par chaque utilisateur, entrer 10");
        
	 }
	 private boolean userAdmin(User user) {
		return gestionUserService.userAdmin(user);
	 }
	 
	  private void sortirDeLApplication() {
	        System.exit(0);
	    }
	  //Afficher la liste de tous les matériels
	  private void listerMateriel() {
		  System.out.println("voici la liste de tous les matériels");
		  gestionMaterielServiceImpl.listerMateriel();
	  }
	  // Chercher un matériel
	  public void chercherMateriel() {
		  System.out.println("Veuillez saisir l'id du materiel");
		  String idString;
		  Long id = null;
		  int verif=0;
		do  {
			  idString=scanner.next();
		  try {
			 
			  id =Long.parseLong(idString);
			  verif=1;
		  }
		  catch(Exception e) {
			  System.out.println("le type est invalide,entrer un nombre");
			
		  }}while(verif==0);
		
	    	 gestionMaterielServiceImpl.chercherMateriel(id);
	    }
	  //Allouer un matériel
	  public void allouerMateriel(User user) {
		 String name;
		  do {
			  System.out.println("Veuillez saisir le nom du materiel ,entrer livre ou chaise");//car on a seulement de type de materiel chaise et livre
  																								//donc il ne faut pas entrer un nom quelconque
			  name= scanner.next().toLowerCase(); 
			  
		  }while(!"livre".equals(name) && !"chaise".equals(name));
		  System.out.println("saisir la date de l'allocation");
		  String date=scanner.next();
		  gestionMaterielServiceImpl.alloue(name,user.getId(),date);
		 
	  }
	  //Afficher la liste des matériels alloués par lui même
	  public void ListerMesAllocations(User user) {
		  gestionMaterielServiceImpl.ListerMesAllocations(user);
	  }
	  //Rendre un matériel
	  public void rendreMateriel(Long idUser) {
		  System.out.println("Veuillez saisir l'id du materiel");
		  Long idMateriel= scanner.nextLong();
	    	 gestionMaterielServiceImpl.rendreMateriel(idUser,idMateriel);
	  }
	  // Créer un nouveau matériel
	  public void  creerMateriel() {
		  String name;
		  Materiel nouveauMateriel;
		  do {
			  System.out.println("Veuillez saisir le nom du materiel ,entrer livre ou chaise");//car on a seulement de type de materiel chaise et livre
  																								//donc il ne faut pas entrer un nom quelconque
			  name= scanner.next().toLowerCase(); 
			  
		  }while(!"livre".equals(name) && !"chaise".equals(name));
		  if ("livre".equals(name)) nouveauMateriel=new Livre();
	        else nouveauMateriel=new Chaise();
	        System.out.println("saisir le code du materiel");
	        nouveauMateriel.setCode(scanner.next());
	        gestionMaterielServiceImpl.ajouterNouveauMateriel(nouveauMateriel);
		  
	  }
	  //Supprimer un matériel
	  public void supprimerMateriel() {
		  System.out.println("Veuillez saisir l'id du materiel");
		  String idString;
		  Long id = null;
		  int verif=0;
		do  {
			  idString=scanner.next();
		  try {
			 
			  id =Long.parseLong(idString);
			  verif=1;
		  }
		  catch(Exception e) {
			  System.out.println("le type est invalide,entrer un nombre");
			
		  }}while(verif==0);
		  gestionMaterielServiceImpl.supprimerMateriel(id);
	  }
	  //Modifier les informations d'un matériel
	  public void  modifierMateriel() {
		  System.out.println("Veuillez saisir l'id du materiel");
		  String idString;
		  Long id = null;
		  int verif=0;
		do  {
			  idString=scanner.next();
		  try {
			 
			  id =Long.parseLong(idString);
			  verif=1;
		  }
		  catch(Exception e) {
			  System.out.println("le type est invalide,entrer un nombre");
			
		  }}while(verif==0);
		 String name;
		 do {
			  System.out.println("Veuillez saisir le nouveau nom, entrer livre ou chaise");//car on a seulement de type de materiel chaise et livre
 																								//donc il ne faut pas entrer un nom quelconque
			  name= scanner.next().toLowerCase(); 
			  
		  }while(!"livre".equals(name) && !"chaise".equals(name));
		 System.out.println("Veuillez saisir le nouveau code"); 
		 String code= scanner.next();
		  gestionMaterielServiceImpl.modifierMateriel(id,name,code);
	
	  }
	  //Marquer un matériel indisponible
	  public void  indisponibleMateriel() {
		  System.out.println("Veuillez saisir l'id du materiel");
		  String idString;
		  Long id = null;
		  int verif=0;
		do  {
			  idString=scanner.next();
		  try {
			 
			  id =Long.parseLong(idString);
			  verif=1;
		  }
		  catch(Exception e) {
			  System.out.println("le type est invalide,entrer un nombre");
			
		  }}while(verif==0);
		  gestionMaterielServiceImpl.indisponibleMateriel(id);
	  }
	  //Afficher la liste des matériels alloués par chaque utilisateur
	  public void  listerMaterielAlloue() {
		  gestionMaterielServiceImpl.listerMaterielAlloue();
	  }

}
