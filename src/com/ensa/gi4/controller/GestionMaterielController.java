package com.ensa.gi4.controller;

import com.ensa.gi4.PropertiesConfigurer;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    GestionUserService GUS;

    @Autowired
    GestionMaterielService GMS;
    
    @Value("${msg.messageErreur}")
    String messageErreur;
    
	User user;
    
	public User Authentification() {
		System.out.println("---------------Connexion------------------");
		System.out.print("Nom : ");
    	Scanner scannerUtilisateur  = new Scanner(System.in);
        String nom = scannerUtilisateur.next();
        System.out.print("Mot de passe : ");
        String password = scannerUtilisateur.next();
        password = GUS.Hashing(password);
        user = GUS.Connexion(nom, password);
		if(user!=null) {
			return user;	
		}else {
			System.out.println(messageErreur);
	    	System.out.println("0- saisir 0 pour sortir de l'application");
	        System.out.println("1- saisir 1 pour réesayer à nouveau");
	    	String next = scannerUtilisateur.next();
	    	if(next.equals("0")) {
	    	    sortirDeLApplication();
	    	}else if("1".equals(next)) {
	    		user = null;
	    	}
	    	return null;
		}
	}
	
	
	public void afficherMenu(User user) {
		if(user.getRole().equals("ADMIN")) {
			afficherMenuAdmin();
		} else {
			afficherMenuEmp();
		}
	}
	
	
	
	private void afficherMenuAdmin() {
		System.out.println("--------------- Welcome "+user.getNom()+" ---------------");
        System.out.println("1- Chercher un matériel");
        System.out.println("2- Créer un nouveau matériel");
        System.out.println("3- Supprimer un matériel");
        System.out.println("4- Modifier les informations d'un matériel");
        System.out.println("5- Marquer un matériel indisponible");
        System.out.println("6- Allouer un matériel");
        System.out.println("7- Rendre un materiel");
        System.out.println("8- Afficher la liste des matériels alloués par lui même");
        System.out.println("9- Afficher la liste des matériels alloués par chaque utilisateur");
        System.out.println("10- Afficher la liste des matériels");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner  = new Scanner(System.in);
        String next = scanner.next();	
        if ("0".equals(next)) {
            sortirDeLApplication();
        }else if("1".equals(next)) {
       	 System.out.print("Saisir ID du materiel : ");            	            	
            int ID = scanner.nextInt();
            GMS.chercherMateriel(ID);
        }else if("2".equals(next)) {
       	    System.out.print("Saisir le nom du materiel : ");
       	    String nom = scanner.next();
       	    System.out.print("Saisir le type du materiel : ");
       	    String type = scanner.next();
       	    Materiel materiel=new Livre();
       	    materiel.setName(nom);
            materiel.setCode(type);
       	    GMS.ajouterNouveauMateriel(materiel);
        }else if("3".equals(next)) {
       	    System.out.print("Saisir ID du materiel : ");
       	    int ID = scanner.nextInt();
       	    GMS.supprimerMateriel(ID);
        }else if("4".equals(next)) {
       	    System.out.print("Saisir ID du materiel : ");
       	    int ID = scanner.nextInt();
       	    System.out.print("Nouveau nom : ");
       	    String nouveauNom=scanner.next();
       	    System.out.print("Nouveau code : ");
       	    String nouveauCode=scanner.next();
       	    GMS.modifierMateriel(ID, nouveauNom, nouveauCode);
        }else if("5".equals(next)) {
       	    System.out.print("Saisir ID du materiel qui vous voulez marquer indispoible : ");
       	    int idMateriel=scanner.nextInt();
       	    GMS.marquerMaterielIndisponible(idMateriel);
        }else if("6".equals(next)) {
       	    System.out.print("Saisir ID du materiel : ");
       	    int idMateriel = scanner.nextInt();
       	    System.out.print("Saisir date de debut (yyyy-mm-dd) : ");
       	    String dateDebut = scanner.next();
       	    System.out.print("Saisir date de fin (yyyy-mm-dd) : ");
       	    String dateFin = scanner.next();
       	    GMS.allouerMateriel(idMateriel, dateDebut, dateFin, user.getId());
        }else if("7".equals(next)) {
       	    System.out.print("Saisir ID du materiel : ");
       	    int idMateriel=scanner.nextInt();
       	    GMS.rendreMateriel(idMateriel);
        }else if("8".equals(next)) {
        	GMS.listeMaterielAlloue(user.getId());
        }else if("9".equals(next)) {
        	GMS.listeMaterielAlloueAll();
        }else if("10".equals(next)) {
        	GMS.listerMateriel();
        }else {
            System.out.println("Choix invalide !!!");
        }
        System.out.println("------------------------------------------------------");    

	}

	
    private void afficherMenuEmp() {
		System.out.println("--------------- Welcome "+user.getNom()+" ---------------");
		System.out.println("1- Chercher un matériel");
        System.out.println("2- Allouer un matériel");
        System.out.println("3- Rendre un materiel");
        System.out.println("4- Afficher la liste des matériels alloués par lui même");
        System.out.println("5- Afficher la liste de tous les matériels");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner  = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        }else if("1".equals(next)) {
       	    System.out.print("Saiasir ID du materiel : ");            	            	
            int ID = scanner.nextInt();
            GMS.chercherMateriel(ID);
        }else if("2".equals(next)) {
       	     System.out.print("Saisir ID du materiel : ");
       	     int idMateriel=scanner.nextInt();
        	 System.out.print("Saisir date de debut (yyyy-mm-dd) : ");
           	 String dateDebut = scanner.next();
           	 System.out.print("Saisir date de fin (yyyy-mm-dd) : ");
           	 String dateFin = scanner.next();       	     
           	 GMS.allouerMateriel(idMateriel, dateDebut, dateFin, user.getId());
        }else if("3".equals(next)) {
       	     System.out.print("Saisir ID du materiel : ");
       	     int idMateriel=scanner.nextInt();
       	     GMS.rendreMateriel(idMateriel);
        }else if("4".equals(next)) {
        	 GMS.listeMaterielAlloue(user.getId());
        }else if("5".equals(next)) {
       	     GMS.listerMateriel();
        }		
	}

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
