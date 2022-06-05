package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPrincipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    private GestionMaterielServiceImpl gestionMaterielService;
    public void afficherMenu() {
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour chercher materiel par id, entrer 2");
        System.out.println("3- pour ajouter materiel, entrer 3");
        System.out.println("4- pour supprimer materiel, entrer 4");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
//          publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
            gestionMaterielService.listerMateriel();
        } else if ("2".equals(next)) {
            System.out.println("Entrez le numero du matériel à rechercher : ");
            Scanner sc = new Scanner(System.in);
            long numero = Long.parseLong(sc.next());
            gestionMaterielService.chercherMateriel(numero);
        } else if ("3".equals(next)) {
            Materiel nouveau = new Materiel();
            System.out.println("\nEntrez le numero du nouveau matériel : ");
            Scanner sc1 = new Scanner(System.in);
            int num = sc1.nextInt();
            nouveau.setIdMateriel(num);

            System.out.println("Le nom : ");
            Scanner sc2 = new Scanner(System.in);
            String nom = sc2.next();
            nouveau.setMaterielName(nom);

            System.out.println("Le code : ");
            Scanner sc3 = new Scanner(System.in);
            String code = sc3.next();
            nouveau.setMaterielCode(code);

            System.out.println("Le type : ");
            Scanner sc4 = new Scanner(System.in);
            String type = sc4.next();
            nouveau.setMaterielType(type);

            System.out.println("La disponibilité : ");
            Scanner sc5 = new Scanner(System.in);
            Boolean dispo = Boolean.valueOf(sc5.next());
            nouveau.setDisponible(dispo);

            gestionMaterielService.ajouterMateriel(nouveau);
        }else if ("4".equals(next)) {
            System.out.println("Entrez le numero du matériel à supprimer : ");
            Scanner sc6 = new Scanner(System.in);
            int numsupp = sc6.nextInt();
            gestionMaterielService.supprimerMateriel(numsupp);
        }  else {
            System.out.println("choix invalide");
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
