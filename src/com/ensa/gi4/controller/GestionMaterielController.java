package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Admin;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Employee;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
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
    
	public Utilisateur connection() {
		Utilisateur utilisateur;
		System.out.println("---------------Connexion------------------");
		System.out.println("Nom :");
    	Scanner scannerUtilisateur  = new Scanner(System.in);
        String nom = scannerUtilisateur.next();
        System.out.println("Password :");
        String password = scannerUtilisateur.next();
		if(gestionUtilisateurService.connexion(nom, password)!=null) {
			if(gestionUtilisateurService.connexion(nom, password).getRole().equals("admin")) {
				utilisateur=new Admin();
				utilisateur.setUsername(nom);
				utilisateur.setPassword(password);
				utilisateur.setRole("admin");
				return utilisateur;
			}else {
				utilisateur=new Employee();
				utilisateur.setUsername(nom);
				utilisateur.setPassword(password);
				utilisateur.setRole("employe");
				return utilisateur;
			}
		}else {
			System.out.println("Les données n'existe pas dans la base données");
			return null;
		}
	}
	
    public void afficherMenu(String nom, String password, String role) {
    	if(role.equals("admin")) {
    		System.out.println("---------------Welcome Admin------------------");
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
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saiasir ID du materiel");            	            	
                 Long ID = scannerAdmin.nextLong();
                 gestionMaterielService.chercherMateriel(ID);
             }else if("2".equals(next)) {
            	 System.out.println("Choisir le nom du materiel 1-Livre 2-Chaise");
            	 String choi=scannerAdmin.next();
            	 String nomMateriel=null;
            	 if("1".equals(choi)) {
            		 System.out.println("Saisir le code du materiel");
                	 String code=scannerAdmin.next();
                	 Materiel materiel=new Livre();
                	 materiel.setName("LIVRE");
                	 materiel.setCode(code);
                	  gestionMaterielService.ajouterNouveauMateriel(materiel);
            	 }else if("2".equals(choi)) {
            		 System.out.println("Saisir le code du materiel");
                	 String code=scannerAdmin.next();
                	 Materiel materiel=new Chaise();
                	 materiel.setName("CHAISE");
                	 materiel.setCode(code);
                	  gestionMaterielService.ajouterNouveauMateriel(materiel);
            	 }else {
            		System.out.println("Ce choi n'existe pas"); 
            	 }
             }else if("3".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 if(gestionMaterielService.isExiste(idMateriel)) {
            		 gestionMaterielService.deleteMateriel(idMateriel);
            	 }else {
            		 System.out.println("Ce materiel n'existe pas");
            	 }
            	 
             }
             else if("4".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            		 System.out.println("Saisir ID du materiel");
                	 Long idMateriel=scannerAdmin.nextLong();
                 if(gestionMaterielService.isExiste(idMateriel)) {
                	 System.out.println("Nouveau code");
                	 String nouveauCode=scannerAdmin.next();
                     gestionMaterielService.modifierMateriel(idMateriel, nouveauCode);
            	 }else {
            		 System.out.println("Ce materiel n'existe pas");
            	 }
             }else if("5".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel qui vous voulez marquer indispoible");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 if(gestionMaterielService.isExiste(idMateriel)) {
            		 gestionMaterielService.marquerMaterielIndisponible(idMateriel);
            	 }else {
            		 System.out.println("Ce materiel n'existe pas");
            	 }
             }else if("6".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 if(gestionMaterielService.isDisponible(idMateriel)) {
            		 if(!gestionMaterielService.isAlloue(idMateriel)) {
                		 System.out.println("Saisir dure");
                    	 String dure=scannerAdmin.next();
                    	 gestionMaterielService.allouerMateriel(idMateriel, dure, gestionUtilisateurService.connexion(nom, password).getId(), gestionUtilisateurService.connexion(nom, password).getUsername());
                	 }else {
                		 System.out.println("Le materiel déja alloué");
                	 }
            	 }else {
            		 System.out.println("Le materiel n'est pas disponible");
            	 }
            	
             }else if("7".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerAdmin.nextLong();
            	 if(gestionMaterielService.isExiste(idMateriel)) {
            		 gestionMaterielService.rendreMateriel(idMateriel);
            	 }else {
            		 System.out.println("Ce materiel n'existe pas");
            	 }
             }else if("8".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloue(gestionUtilisateurService.connexion(nom, password).getId());
             }else if("9".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloueAll();
             }
             else {
                 System.out.println("Choix invalide !!!");
             }
    	}
    	
    	
    	if(role.equals("employe")) {
    		System.out.println("---------------Welcome "+nom+"------------------");
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
             }else if("1".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saiasir ID du materiel");            	            	
                 Long ID = scannerEmploye.nextLong();
                 gestionMaterielService.chercherMateriel(ID);
             }else if("2".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerEmploye.nextLong();
            	 if(gestionMaterielService.isDisponible(idMateriel)) {
            		 if(!gestionMaterielService.isAlloue(idMateriel)) {
                		 System.out.println("Saisir dure");
                    	 String dure=scannerEmploye.next();
                    	 gestionMaterielService.allouerMateriel(idMateriel, dure, gestionUtilisateurService.connexion(nom, password).getId(), gestionUtilisateurService.connexion(nom, password).getUsername());
                	 }else {
                		 System.out.println("Le materiel déja alloué");
                	 }
            	 }else {
            		 System.out.println("Le materiel n'est pas disponible");
            	 }
            	
             }else if("3".equals(next)) {
            	 gestionMaterielService.listerMateriel();
            	 System.out.println("Saisir ID du materiel");
            	 Long idMateriel=scannerEmploye.nextLong();
            	 if(gestionMaterielService.isExiste(idMateriel)) {
            		 gestionMaterielService.rendreMateriel(idMateriel);
            	 }else {
            		 System.out.println("Ce materiel n'existe pas");
            	 }
             }else if("4".equals(next)) {
            	 gestionMaterielService.listeMaterielAlloue(gestionUtilisateurService.connexion(nom, password).getId());
             }else if("5".equals(next)) {
            	 gestionMaterielService.listerMateriel();
             }else {
                 System.out.println("Choix invalide !!!");
             }
    	}
    	
      }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
