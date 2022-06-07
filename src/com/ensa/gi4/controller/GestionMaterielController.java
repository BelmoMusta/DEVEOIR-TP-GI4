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
    
    public void afficherMenu(String nom, String password) {
    	if(gestionUtilisateurService.connexion(nom, password)!=null && gestionUtilisateurService.connexion(nom, password).getRole().equals("admin")) {
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
             Scanner scannerAdmin  = new Scanner(System.in);
             String next = scannerAdmin.next();
             if ("0".equals(next)) {
                 sortirDeLApplication();
             } else if("1".equals(next)) {
            	 System.out.println("Saiasir ID du materiel");            	            	
                 Long ID = scannerAdmin.nextLong();
                 gestionMaterielService.chercherMateriel(ID);
             }else if("2".equals(next)) {
            	 System.out.println("Saisir le nom du materiel");
            	 String nomMateriel=scannerAdmin.next();
            	 System.out.println("Saisir le code du materiel");
            	 String code=scannerAdmin.next();
            	 Materiel materiel=new Livre();
            	 materiel.setName(nomMateriel);
            	 materiel.setCode(code);
            	 gestionMaterielService.ajouterNouveauMateriel(materiel);
             }else if("3".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 gestionMaterielService.deleteMateriel(idMateriel);
             }
             else if("4".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
             }else if("5".equals(next)) {
            	 System.out.println("Saisir ID du materiel qui vous voulez marquer indispoible");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 gestionMaterielService.marquerMaterielIndisponible(idMateriel);
             }else if("6".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 System.out.println("Saisir dure");
            	 String dure=scannerAdmin.next();
            	 gestionMaterielService.allouerMateriel(idMateriel, dure, gestionUtilisateurService.connexion(nom, password).getId());
             }else if("7".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 gestionMaterielService.rendreMateriel(idMateriel);
             }else if("8".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloue(gestionUtilisateurService.connexion(nom, password).getId());
             }else if("9".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloueAll();
             }
             else {
                 System.out.println("Choix invalide !!!");
             }
             System.out.println("------------------------------------------------------");    
    	}
    	
    	
    	if(gestionUtilisateurService.connexion(nom, password)!=null && gestionUtilisateurService.connexion(nom, password).getRole().equals("employe")) {
    		 System.out.println("1- Chercher un matériel");
             System.out.println("2- Allouer un matériel");
             System.out.println("3- Rendre un materiel");
             System.out.println("4- Afficher la liste des matériels alloués par lui même");
             System.out.println("5- Afficher la liste de tous les matériels");
             System.out.println("0- pour sortir de l'application, entrer 0");
             Scanner scannerEmploye  = new Scanner(System.in);
             String next = scannerEmploye.next();
             if ("0".equals(next)) {
                 sortirDeLApplication();
             } else if("1".equals(next)) {
            	 System.out.println("Saiasir ID du materiel");            	            	
                 Long ID = scannerEmploye.nextLong();
                 gestionMaterielService.chercherMateriel(ID);
             }else if("2".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerEmploye.nextLong();
            	 System.out.println("Saisir dure");
            	 String dure=scannerEmploye.next();
            	 gestionMaterielService.allouerMateriel(idMateriel, dure, gestionUtilisateurService.connexion(nom, password).getId());
             }else if("3".equals(next)) {
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerEmploye.nextLong();
            	 gestionMaterielService.rendreMateriel(idMateriel);
             }
             else if("4".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloue(gestionUtilisateurService.connexion(nom, password).getId());
             }else if("5".equals(next)) {
            	 gestionMaterielService.listerMateriel();
             }
    	}
    	
      }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
