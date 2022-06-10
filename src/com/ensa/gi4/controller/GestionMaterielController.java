package com.ensa.gi4.controller;


import com.ensa.gi4.modele.Person;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.impl.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;
    @Autowired
    private Register register;

    Person yourRole;

    public void afficherMenu(){

        System.out.println("1- Pour se connecter , entrer 1");
//        System.out.println("2- Pour créer un compte, entrer 2");
        System.out.println("0- Pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        }
        else if ("1".equals(next)) {
            yourRole = register.signInPerson();

            if(yourRole.getRole().equals("admin"))
            {
                afficherMenuAdmin();
            }
            else if(yourRole.getRole().equals("user"))
            {
                afficherMenuUser();
            }
            else {
                sortirDeLApplication();
            }
        }
//        else if ("2".equals(next)) {
//            yourRole = register.signUpPerson();
//            afficherMenuUser();
//        }


    }

    public void afficherMenuAdmin(){

        System.out.println("------------------Bienvenue dans le menu d'admin-----------------");
        System.out.println("1- Pour lister tous les matériels, entrer 1");
        System.out.println("2- Pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- Pour chercher un matériel, entrer 3");
        System.out.println("4- Pour supprimer un matériel, entrer 4");
        System.out.println("5- Pour modifier un matériel, entrer 5");
        System.out.println("6- Pour allouer un matériel, entrer 6");
        System.out.println("7- Pour rendre un matériel alloué, entrer 7");
        System.out.println("8- Pour lister les matériels alloués via le userId, entrer 8");
        System.out.println("9- Pour lister tous les matériels alloués , entrer 9");
        System.out.println("10- Pour se déconnecter , enter 10");
        System.out.println("0- Pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            gestionMaterielService.listerMateriel();
        } else if ("2".equals(next)) {
            gestionMaterielService.ajouterNouveauMateriel();
        }
        else if ("3".equals(next)) {
            gestionMaterielService.chercherMateriel();
        }
        else if("4".equals(next))
        {
            gestionMaterielService.supprimerMateriel();
        }
        else if("5".equals(next))
        {
            gestionMaterielService.modifierMateriel();
        }
        else if("6".equals(next))
        {
            gestionMaterielService.allouerMateriel(yourRole);
        }
        else if("7".equals(next))
        {
            gestionMaterielService.rendreMateriel();
        }
        else if("8".equals(next))
        {
            gestionMaterielService.listerMaterielAllouerUserId();
        }
        else if("9".equals(next))
        {
            gestionMaterielService.listerMaterielAllouer();
        }
        else if("10".equals(next))
        {
            System.out.println("------------------Vous êtes déconnecté!-----------------");
            afficherMenu();
        }
        else {
            System.out.println("Votre choix est invalide!");
        }

        afficherMenuAdmin();
    }

    public void afficherMenuUser() {

        System.out.println("------------------Bienvenue dans le menu d'Employé-----------------");
        System.out.println("1- Pour lister tous les matériels, entrer 1");
        System.out.println("2- Pour chercher un matériel, entrer 2");
        System.out.println("3- Pour allouer un matériel, entrer 3");
        System.out.println("4- Pour rendre un matériel alloué, entrer 4");
        System.out.println("5- Pour lister les matériels que vous avez alloué , entrer 5");
        System.out.println("6- Pour se déconnecter , enter 6");
        System.out.println("0- Pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            gestionMaterielService.listerMateriel();
        }
        else if ("2".equals(next)) {
            gestionMaterielService.chercherMateriel();
        }
        else if("3".equals(next))
        {
            gestionMaterielService.allouerMateriel(yourRole);
        }
        else if("4".equals(next))
        {
            gestionMaterielService.rendreMateriel();
        }
        else if("5".equals(next))
        {
            gestionMaterielService.listerMaterielAllouerYourUserId(yourRole);
        }
        else if("6".equals(next))
        {
            System.out.println("------------------Vous êtes déconnecté!-----------------");
            afficherMenu();
        }
        else {
            System.out.println("Votre choix est invalide!");
        }

        afficherMenuUser();
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }
}
