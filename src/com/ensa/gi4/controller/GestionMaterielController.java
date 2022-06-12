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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
	@Autowired
    ApplicationPublisher publisher;
    @Autowired
	GestionUserService gestionUserService ;
	@Autowired
	GestionMaterielService gestionMaterielServiceImpl;
	 Scanner scanner = new Scanner(System.in);
	 // Externaliser, les messages en dur sur des fichiers properties et les charger au démarrage; 
	 @Value("${entrerNom}") private String entrerNom;
	 @Value("${entrerPassword}") private String entrerPassword;
	 @Value("${choix0}") private String choix0;
	 @Value("${choix1}") private String choix1;
	 @Value("${choix2}") private String choix2;
	 @Value("${choix3}") private String choix3;
	 @Value("${choix4}") private String choix4;
	 @Value("${choix5}") private String choix5;
	 @Value("${choix6}") private String choix6;
	 @Value("${choix7}") private String choix7;
	 @Value("${choix8}") private String choix8;
	 @Value("${choix9}") private String choix9;
	 @Value("${choix10}") private String choix10;
	 @Value("${menu}") private String menu;
	 @Value("${invalide}") private String invalide ;
	 @Value("${afficherMenu}") private String afficherMenu ;
	 @Value("${liste}") private String liste ;
	 @Value("${dateAllocation}") private String dateAllocation ;
	 @Value("${codeMateriel}") private String codeMateriel ;
	 @Value("${nouveauCode}") private String nouveauCode ;
	 @Value("${saisirId}") private String  saisirId ;
	 @Value("${invalideType}") private String  invalideType ;
	 @Value("${nom_materiel}") private String   nom_materiel ;
	
	 //fin d Externalisation
	 public User connecter() {
		 System.out.println(entrerNom);
	      String name= scanner.next();
	      System.out.println(entrerPassword);
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
    	        else {System.out.println(invalide);}
    		   }
	        else {System.out.println(invalide);}
    	  System.out.println("*********************************************************");
 		 System.out.println(afficherMenu);
 		  next = scanner.next();
 		  if ("1".equals(next)) {
 			 afficherMenuPrincipale(userActuelle) ;
          }
         else  {
        	 sortirDeLApplication();
	            
	        }
	 	}
	 
	 
	 public void afficherMenuUser() {
		 System.out.println(menu);
		 System.out.println(choix0);
		 System.out.println(choix1);
         System.out.println(choix2);
         System.out.println(choix3);
         System.out.println(choix4);
         System.out.println(choix5);
        
	 }
	 public void afficherMenuAdmin() {
		 System.out.println(choix6);
         System.out.println(choix7);
         System.out.println(choix8);
         System.out.println(choix9);
         System.out.println(choix10);
        
	 }
	 private boolean userAdmin(User user) {
		return gestionUserService.userAdmin(user);
	 }
	 
	  private void sortirDeLApplication() {
	        System.exit(0);
	    }
	  //Afficher la liste de tous les matériels
	  private void listerMateriel() {
		  System.out.println(liste);
		  gestionMaterielServiceImpl.listerMateriel();
	  }
	  // Chercher un matériel
	  public void chercherMateriel() {
		  Long id=idValide();
		   gestionMaterielServiceImpl.chercherMateriel(id);
	    }
	  //Allouer un matériel
	  public void allouerMateriel(User user) {
		 String name=nameValide();
		  System.out.println(dateAllocation);
		  String date=scanner.next();
		  gestionMaterielServiceImpl.alloue(name,user.getId(),date);
		 
	  }
	  //Afficher la liste des matériels alloués par lui même
	  public void ListerMesAllocations(User user) {
		  gestionMaterielServiceImpl.ListerMesAllocations(user);
	  }
	  //Rendre un matériel
	  public void rendreMateriel(Long idUser) {
		  Long idMateriel=idValide();
	    	 gestionMaterielServiceImpl.rendreMateriel(idUser,idMateriel);
	  }
	  // Créer un nouveau matériel
	  public void  creerMateriel() {
		  String name=nameValide();
		  Materiel nouveauMateriel;
		  if ("livre".equals(name)) { nouveauMateriel=new Livre();
		  }
	        else {nouveauMateriel=new Chaise();
	        }
	        System.out.println(codeMateriel);
	        nouveauMateriel.setCode(scanner.next());
	        gestionMaterielServiceImpl.ajouterNouveauMateriel(nouveauMateriel);
	        publisher.publish(new MyEvent<>(nouveauMateriel, EventType.ADD));
		  
	  }
	  //Supprimer un matériel
	  public void supprimerMateriel() {
		  Long id=idValide();
		 if( gestionMaterielServiceImpl.trouverMateriel(id)!=null)
		  publisher.publish(new MyEvent<>(gestionMaterielServiceImpl.trouverMateriel(id), EventType.REMOVE));
		  gestionMaterielServiceImpl.supprimerMateriel(id);
	  }
	  //Modifier les informations d'un matériel
	  public void  modifierMateriel() {
		 Long id=idValide();
		 String name=nameValide();
		 System.out.println(nouveauCode); 
		 String code= scanner.next();
		  gestionMaterielServiceImpl.modifierMateriel(id,name,code);
		  if( gestionMaterielServiceImpl.trouverMateriel(id)!=null)
			  publisher.publish(new MyEvent<>(gestionMaterielServiceImpl.trouverMateriel(id), EventType. UPDATE));
	
	  }
	  //Marquer un matériel indisponible
	  public void  indisponibleMateriel() {
		Long id=idValide();
		  gestionMaterielServiceImpl.indisponibleMateriel(id);
	  }
	  //id doit etre un nombre
	
	public  Long  idValide() {
		  
		  
		  System.out.println(saisirId);
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
			  System.out.println(invalideType);
			
		  }}while(verif==0);
		
		  return id;
		  
	  }
	//le nom du materiel doit etre livre ou chaise ,car on a seulement ces 2 types
	public String nameValide() {
		String name;
		  do {
		 System.out.println(nom_materiel);
		    name= scanner.next().toLowerCase(); 
		   }while(!"livre".equals(name) && !"chaise".equals(name));
		  return name;
	}
	  //Afficher la liste des matériels alloués par chaque utilisateur
	  public void  listerMaterielAlloue() {
		  gestionMaterielServiceImpl.listerMaterielAlloue();
	  }

}
