package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    
	@Autowired
    ApplicationPublisher publisher;
	
	@Autowired
	GestionUtilisateurService gestionUtilisateurService;
	
	@Autowired
	@Qualifier("materielService")
	private GestionMaterielService gestionMaterielService;
    
    public void afficherMenu() {
    	System.out.println("Saiasir votre nom");
    	Scanner scanner  = new Scanner(System.in);
        String nom = scanner.next();
        System.out.println("Saisir votre password ");
        String password = scanner.next();
    	if(gestionUtilisateurService.connexion(nom, password)!=null) {
    	/*	 Scanner scanner  = new Scanner(System.in);
             publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));*/
        	 System.out.println("1- Chercher un matériel");
             System.out.println("2- Créer un nouveau matériel");
             System.out.println("3- Supprimer un matériel");
             System.out.println("4- Modifier les informations d'un matériel");
             System.out.println("5- Marquer un matériel indisponible");
             System.out.println("6- Allouer un matériel");
             System.out.println("7- Rendre un materiel");
             System.out.println("8- Afficher la liste des matériels alloués par lui même");
             System.out.println("9- Afficher la liste des matériels alloués par chaque utilisateur");
             System.out.println("0- pour sortir de l'application, entrer 0");
             String next = scanner.next();
             if ("0".equals(next)) {
                 sortirDeLApplication();
             } else if ("10".equals(next)) {
               gestionMaterielService.listerMateriel();
             }else if("1".equals(next)) {
            	 System.out.println("Saiasir ID du materiel");            	            	
                 Long ID = scanner.nextLong();
                 gestionMaterielService.chercherMateriel(ID);
             }else if("2".equals(next)) {
            	 System.out .println("Saisir le nom du materiel");
            	 String nomMateriel=scanner.next();
            	 System.out.println("Saisir le code du materiel");
            	 String code=scanner.next();
            	 System.out.println("Saisir la quantite du materiel");
            	 int quantite=scanner.nextInt();
            	 Materiel materiel=new Livre();
            	 materiel.setName(nomMateriel);
            	 materiel.setCode(code);
            	 materiel.setQuantite(quantite);
            	 gestionMaterielService.ajouterNouveauMateriel(materiel);
             }else if("3".equals(next)) {
            	 System.out .println("Saisir ID du materiel");
            	 Long idMateriel=scanner.nextLong();
            	 gestionMaterielService.deleteMateriel(idMateriel);
             }
             else {
                 System.out.println("Choix invalide !!!");
             }
             System.out.println("------------------------------------------------------");
             
            
    	}
    /*	else {
    	  System.out.println(" saisir 0 pour sortir de l'application");
       	  System.out.println(" saisir 1 pour réesayer à nouveau");
       	  String choix = scanner.next();
       	  if(choix.equals("0")) {
       		  sortirDeLApplication();
       	  }else {
       		  nom=null;
       		  password=null;
       		  afficherMenu();
       	  }
    	}
    	*/
      }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
