package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.impl.UtilisateurDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielServiceImpl;

    @Autowired
    private UtilisateurDaoImpl log;

    Utilisateur user;

    public void MENU() {

        System.out.println("1-  Connecter, entrer 1");
        System.out.println("0- Sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        
        if ("0".equals(next)) {
            sortie();
        }
        else if("1".equals(next))
        {
            user = log.find_user();

            if(user.getRole().equals("admin")){
                while (true)
                	ADMIN();
            }
            else if (user.getRole().equals("user")){
                while (true)
                    USER();
            }
            else {
                sortie();
            }
        }

        else{
        	sortie();
        }

    }

    private void sortie() {
        System.exit(0);
    }







    public void ADMIN(){
    	System.out.println("---------------Welcome Admin------------------");
    	  System.out.println("0- pour sortir de l'application, entrer 0");
   	 System.out.println("1- Chercher un matÃ©riel, tapper 1");
        System.out.println("2- Créer un nouveau matériel, tapper 2");
        System.out.println("3- Supprimer un matériel, tapper 3");
        System.out.println("4- Modifier les informations d'un matériel, tapper 4");
        System.out.println("5- Marquer un matériel indisponible, tapper 5");
        System.out.println("6- Allouer un matériel, tapper 6");
        System.out.println("7- Rendre un materiel, tapper 7");
        System.out.println("8- Afficher la liste des matériels alloués par vous, tapper 8");
        System.out.println("9- Afficher la liste des matériels alloués par chaque utilisateur, tapper 9");
        System.out.println("10- Afficher la liste de tout les materiel, tapper 10");
        System.out.println("11- Deconnixion, tapper 11");
        Scanner scannerAdmin  = new Scanner(System.in);
        String next = scannerAdmin.next();
        if ("0".equals(next)) {
        	sortie();
        } else if("1".equals(next)) {

        	gestionMaterielServiceImpl.listerMateriel();
       	 System.out.println("Saiasir ID du materiel");
            Long ID = scannerAdmin.nextLong();
            gestionMaterielServiceImpl.chercherMateriel(ID);

        }else if("2".equals(next)) {
       	 System.out.println("Choisir le nom du materiel Livre(tapper1)  ou Chaise(tapper2)");
       	 String choice=scannerAdmin.next();
       	 String nomMateriel=null;
       	 if("1".equals(choice)) {
       		 System.out.println("Saisir le code du materiel");
           	 String code=scannerAdmin.next();
           	 Materiel materiel=new Livre();
           	 materiel.setName("LIVRE");
           	 materiel.setCode(code);
           	gestionMaterielServiceImpl.ajouterNouveauMateriel(materiel);
       	 }else if("2".equals(choice)) {
       		 System.out.println("Saisir le code du materiel");
           	 String code=scannerAdmin.next();
           	 Materiel materiel=new Chaise();
           	 materiel.setName("CHAISE");
           	 materiel.setCode(code);
           	gestionMaterielServiceImpl.ajouterNouveauMateriel(materiel);
       	 }else {
       		System.out.println("error : choisir un nombre entre 1 et 2");
       	 }
        }else if("3".equals(next)) {
        	gestionMaterielServiceImpl.listerMateriel();;
             System.out.println("Saisir ID du materiel");
             Long idMateriel = (long) scannerAdmin.nextInt();
             if(gestionMaterielServiceImpl.isExiste( idMateriel)){
                 gestionMaterielServiceImpl.supprimerMateriel(idMateriel);
             }
             else {
                 System.out.println("Ce materiel n'existe pas");
             }
        }
        else if("4".equals(next)) {
        	gestionMaterielServiceImpl.listerMateriel();
       		 System.out.println("Saisir ID du materiel");
           	 Long id_M=scannerAdmin.nextLong();
            if(gestionMaterielServiceImpl.isExiste(id_M)) {
            	System.out.println("Nouveau code");
             	 String code_new=scannerAdmin.next();
             	System.out.println("Nouveau name");
             	 String name_new=scannerAdmin.next();

           	gestionMaterielServiceImpl.modifierMateriel(id_M, code_new, name_new);
       	 }else {
       		 System.out.println("Ce materiel n'existe pas");
       	 }
        }else if("5".equals(next)) {
        	 gestionMaterielServiceImpl.listerMateriel();
             System.out.println("Saiasir ID du materiel");
             Long ID = (long) scannerAdmin.nextInt();



       	 if(gestionMaterielServiceImpl.isExiste(ID)) {
       		gestionMaterielServiceImpl.rendreMaterielIndisponible(ID);
       	 }else {
       		 System.out.println("Ce materiel n'existe pas");
       	 }
        }else if("6".equals(next)) {
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            Long ID = scannerAdmin.nextLong();
            if(gestionMaterielServiceImpl.isDisponible(ID)){
                System.out.println("Saiasir la duree d'allocation");
                String duree = scannerAdmin.next();
                gestionMaterielServiceImpl.allouerMateriel(ID, duree, user.getId_utilisateur(), user.getUsername());
                gestionMaterielServiceImpl.rendreMaterielIndisponible(ID);
            }else System.out.println("indisponible ");


        }else if("7".equals(next)) {
        	  gestionMaterielServiceImpl.listerMateriel();
              System.out.println("Saiasir ID du materiel");
              Long ID = scannerAdmin.nextLong();
              gestionMaterielServiceImpl.rendreMateriel(ID);
        }else if("8".equals(next)) {
        	gestionMaterielServiceImpl.listeMaterielAlloue(user.getId_utilisateur());
        }else if("9".equals(next)) {
        	gestionMaterielServiceImpl.listeToutMaterielAlloue();
        }
        else if("10".equals(next)) {
        	gestionMaterielServiceImpl.listerMateriel();
        }
        else if("11".equals(next)) {
        	System.out.println("vous ete deconnecter");
        	MENU();
        }
        else {
            System.out.println("Choix invalide !!!");
        }
    }
    private void USER() {
    	 System.out.println("***********WELCOME USER **********");
    	 System.out.println("0- pour sortir de l'application, entrer 0");
    	 System.out.println("1- Chercher un matériel");
         System.out.println("2- Allouer un matériel");
         System.out.println("3- Rendre un materiel");
         System.out.println("4- Afficher la liste des matériels alloués par cet utilisateur");
         System.out.println("5- Afficher la liste de tout les matteril");
         System.out.println("6-Deconnexion");


        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortie();
        } else if ("1".equals(next)) {
        	 System.out.println("Saiasir ID du materiel");
             Long ID = scanner.nextLong();
             gestionMaterielServiceImpl.chercherMateriel(ID);

        }else if("2".equals(next)){
        	 gestionMaterielServiceImpl.listerMateriel();
             System.out.println("Saiasir ID du materiel");
             Long ID = scanner.nextLong();
             if(gestionMaterielServiceImpl.isDisponible(ID)){
                 System.out.println("Saiasir duree d'allocation");
                 String duree = scanner.next();
                 gestionMaterielServiceImpl.allouerMateriel(ID, duree, user.getId_utilisateur(),user.getUsername());
                 gestionMaterielServiceImpl.rendreMaterielIndisponible(ID);
             }else System.out.println("DEja allouer (indisponile) ");
        }else if("3".equals(next)){
        	 gestionMaterielServiceImpl.listerMateriel();
             System.out.println("Saiasir ID du materiel");
             Long ID = scanner.nextLong();
             gestionMaterielServiceImpl.rendreMateriel(ID);

        }else if("4".equals(next)){
        	gestionMaterielServiceImpl.listeMaterielAlloue(user.getId_utilisateur());
        }else if("5".equals(next)){
        	gestionMaterielServiceImpl.listerMateriel();
        }else if("6".equals(next)) {
        	System.out.println("vous ete deconnecter");
        	MENU();
        }
        else {
            System.out.println("Choix invalide !!!");
        }



    }

    }


