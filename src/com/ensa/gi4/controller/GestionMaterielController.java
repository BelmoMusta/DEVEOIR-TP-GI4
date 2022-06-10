package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    GestionMaterielServiceImpl gestionMaterielServiceImpl;
    @Autowired
     private Login in;

    User user;

    public void listerMateriel() {
        gestionMaterielServiceImpl.listerMateriel();
    }

    public void afficherMenu() {

        System.out.println("1- Se Connecter, entrer 1");
        System.out.println("0- Sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        }
        else if("1".equals(next))
        {
           user = in.login();

           if(user.getRole().equals("admin")){
                while (true)
               afficherMenuOfAdmin();
           }
           else if (user.getRole().equals("user")){
               while (true)
                   afficherMenuOfUser();
           }
           else {
               sortirDeLApplication();
           }
        }

        else{
            sortirDeLApplication();
        }

    }

    private void afficherMenuOfUser() {
        System.out.println("Bienvenu Simo ");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- Chercher un matériel, entrer 2");
        System.out.println("3-  Allouer un matériel, entrer 3");
        System.out.println("4-  Render un matériel, entrer 4");
        System.out.println("5- Afficher la liste des matériels alloués par vous, entrer 5");
        System.out.println("6- se Déconnecter, entre 6");
        System.out.println("0- pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();

        }else if("2".equals(next)){
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            gestionMaterielServiceImpl.chercherMateriel(ID);
        }else if("3".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            if(gestionMaterielServiceImpl.isDispo(ID)){
                System.out.println("Saiasir la duree d'allocation");
                String duree = scanner.next();
                gestionMaterielServiceImpl.materialAlloue(ID,duree,user.getId(),user.getUsername());
                gestionMaterielServiceImpl.materielIndisponible(ID);
            }else System.out.println("indisponible ");


        }else if("4".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            gestionMaterielServiceImpl.renderMateriel(ID);
        }else if("5".equals(next)){
            gestionMaterielServiceImpl.AllMaterielAlloue(user.getId());
        }else if("6".equals(next)){
            afficherMenu();
        }
        else {
            sortirDeLApplication();
        }



    }

    public void afficherMenuOfAdmin(){
        System.out.println("Bienvenu Hamza ");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour supprimer un matériel, entrer 3");
        System.out.println("4- Modifier les informations d'un matériel, entrer 4");
        System.out.println("5- Chercher un matériel, entrer 5");
        System.out.println("6- Marquer un matériel indisponible, entrer 6");
        System.out.println("7-  Allouer un matériel, entrer 7");
        System.out.println("8-  Render un matériel, entrer 8");
        System.out.println("9- Afficher la liste des matériels alloués par vous, entrer 9");
        System.out.println("10- Afficher la liste des matériels alloués par les Utilisateurs, entrer 10");
        System.out.println("11- se Déconnecter, entre 11");
        System.out.println("0- pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();
        } else if ("2".equals(next)) {
            System.out.println("Choisir le type du materiel\n 1-Livre\n 2-Chaise");
            Scanner scanner1 = new Scanner(System.in);
            String choix = scanner1.next();
            if("1".equals(choix)) {

                System.out.println("Saisir le nom du materiel");
                String nom=scanner1.next();
                System.out.println("Saisir le code du materiel");
                String code=scanner1.next();
                Materiel materiel=new Livre();
                materiel.setName(nom);
                materiel.setCode(code);
                materiel.setType("LIVRE");

                gestionMaterielServiceImpl.ajouterNouveauMateriel(materiel);

                publisher.publish(new MyEvent<>(materiel, EventType.ADD));

            }else if("2".equals(choix)) {
                System.out.println("Saisir le nom du materiel");
                String nom=scanner1.next();
                System.out.println("Saisir le code du materiel");
                String code=scanner1.next();
                Materiel materiel=new Chaise();
                materiel.setName(nom);
                materiel.setCode(code);
                materiel.setType("CHAISE");
                gestionMaterielServiceImpl.ajouterNouveauMateriel(materiel);
                publisher.publish(new MyEvent<>(materiel, EventType.ADD));
            }else {
                System.out.println("Ce choix n'existe pas");
            }
        }else if("3".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saisir ID du materiel");
            int idMateriel = scanner.nextInt();
            if(gestionMaterielServiceImpl.checkAvantSupprimer( idMateriel)){
                gestionMaterielServiceImpl.supprimerUnMaterial(idMateriel);

            }
            else {
                System.out.println("Ce materiel n'existe pas");
            }
        }else if("4".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saisir ID du materiel");
            int idMateriel=scanner.nextInt();
            if(gestionMaterielServiceImpl.checkAvantSupprimer(idMateriel)) {
                System.out.println("Nouveau code");
                String nouveauCode=scanner.next();
                System.out.println("Nouveau name");
                String nouveauName=scanner.next();
                gestionMaterielServiceImpl.modifierUnMateriel(idMateriel, nouveauCode, nouveauName);
            }else {
                System.out.println("Ce materiel n'existe pas");
            }
        }else if("5".equals(next)){
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            gestionMaterielServiceImpl.chercherMateriel(ID);
        }else if("6".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            gestionMaterielServiceImpl.materielIndisponible(ID);
        }else if("7".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            if(gestionMaterielServiceImpl.isDispo(ID)){
                System.out.println("Saiasir la duree d'allocation");
                String duree = scanner.next();
                gestionMaterielServiceImpl.materialAlloue(ID,duree,user.getId(),user.getUsername());
                gestionMaterielServiceImpl.materielIndisponible(ID);
            }else System.out.println("indisponible ");


        }else if("8".equals(next)){
            gestionMaterielServiceImpl.listerMateriel();
            System.out.println("Saiasir ID du materiel");
            int ID = scanner.nextInt();
            gestionMaterielServiceImpl.renderMateriel(ID);
        }else if("9".equals(next)){
            gestionMaterielServiceImpl.AllMaterielAlloue(user.getId());
        }else if("10".equals(next)) {
            gestionMaterielServiceImpl.allAllloue();
        }else if("11".equals(next)){
            afficherMenu();
        }

        else {
            sortirDeLApplication();
        }



    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
