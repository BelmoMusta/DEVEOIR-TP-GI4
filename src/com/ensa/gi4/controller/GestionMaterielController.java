package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMaterielService;

    public void afficherMenu() {

        System.out.println("0- pour sortir de l'application, entrer 0");
        System.out.println("1- pour lister les matériels, entrer 1");
        System.out.println("2- pour chercher un matériel avec son id, entrer 2");
        System.out.println("3- pour ajouter un nouveau materiel, entrer 3");
        System.out.println("3- pour supprimer un materiel, entrer 4");
        //System.out.println("4- pour modifier un materiel, entrer 5");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {

            sortirDeLApplication();

        } else if ("1".equals(next)) {

            listerMateriel();

        } else if ("2".equals(next)) {


            System.out.println("Veuillez entrer l'id du materiel recherché : ");
            Scanner sc = new Scanner(System.in);
            long id = Long.parseLong(sc.next());
            gestionMaterielService.chercherMateriel(id);

        } else if ("3".equals(next)) {

            String name, code, type;
            Long stock = Long.valueOf(1);
            boolean dispo = true;

            System.out.print("Veuillez saisir le nom du materiel : ");
            name = scanner.next();

            System.out.print("Veuillez saisir le code du materiel (LI pour les livres et CH pour les chaises): ");
            code = scanner.next();

            System.out.print("Veuillez saisir le type du materiel (Livre pour les livres et Chaise pour les chaises)");
            type = scanner.next();

            System.out.print("Veuillez saisir la quantité en stock du materiel : ");
            stock = scanner.nextLong();

            System.out.print("Veuillez indiquer la disponibilité de materiel : ");
            dispo = scanner.nextBoolean();

            Materiel materiel = new Materiel();

            /*if (code == "LI") {
                materiel = new Livre();
            } else if (code == "CH") {
                materiel = new Chaise();
            }*/


            materiel.setName(name);
            materiel.setCode(code);
            materiel.setType(type);
            materiel.setStock(stock);
            materiel.setDispo(dispo);

            gestionMaterielService.ajouterNouveauMateriel(materiel);

        } else if ("4".equals(next)) {

            Long id;
            System.out.print("Veuillez saisir l'id du materiel à supprimer : ");
            id = scanner.nextLong();
            gestionMaterielService.supprimerMateriel(id);

        }

    }

    private void sortirDeLApplication() {

        System.exit(0);

    }

    public void listerMateriel() {

        gestionMaterielService.listerMateriel();

    }

}
