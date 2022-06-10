package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
    @Autowired
    private GestionMaterielServiceImpl gestionMaterielService;
    @Autowired
    private SignIn signIn;

    User role;

    @Autowired

    ApplicationPublisher publisher;

    public void afficherMenu() {
        System.out.println("1- SignIn, entrer 1");
        System.out.println("0- pour sortir de l'application, entrer 0");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        }
        else if ("1".equals(next)) {
            role = signIn.signInPerson();

            if(role.getRole().equals("admin"))
            {
                afficherMenuAdmin();
            }
            else if(role.getRole().equals("user"))
            {
                afficherMenuUser();
            }
            else {
                sortirDeLApplication();
            }
        }
    }
    public void afficherMenuAdmin(){
        System.out.println("1- pour lister tout les matériels, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour chercher un matériel, entrer 3");
        System.out.println("4- pour supprimer un matériel, entrer 4");
        System.out.println("5- pour modifier un matériel, entrer 5");
        System.out.println("6- pour allouer un matériel, entrer 6");
        System.out.println("7- pour rendre un matériel, entrer 7");
        System.out.println("8- pour lister les matériels allouer via le userId, entrer 8");
        System.out.println("9- pour lister tous les matériels allouer , entrer 9");
        System.out.println("10- pour se deconnecter , enter 10");
        System.out.println("0- pour sortir de l'application, entrer 0");

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
            gestionMaterielService.allouerMateriel(role);
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
            System.out.println("Se Deconnecter");
            afficherMenu();
        }
        else {
            System.out.println("choix invalide");
        }

        afficherMenuAdmin();
    }
    public void afficherMenuUser() {
        System.out.println("1- pour lister tout les matériels, entrer 1");
        System.out.println("2- pour chercher un matériel, entrer 2");
        System.out.println("3- pour allouer un matériel, entrer 3");
        System.out.println("4- pour rendre un matériel, entrer 4");
        System.out.println("5- pour lister les matériels que vous avez allouer , entrer 5");
        System.out.println("6- pour se deconnecter , enter 6");
        System.out.println("0- pour sortir de l'application, entrer 0");

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
            gestionMaterielService.allouerMateriel(role);
        }
        else if("4".equals(next))
        {
            gestionMaterielService.rendreMateriel();
        }
        else if("5".equals(next))
        {
            gestionMaterielService.listerMaterielAllouerYourUserId(role);
        }
        else if("6".equals(next))
        {
            System.out.println("Se Deconnecter");
            afficherMenu();
        }
        else {
            System.out.println("choix invalide");
        }

        afficherMenuUser();
    }
    private void sortirDeLApplication() {
        System.exit(0);
    }

}
