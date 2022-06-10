package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionAllocationMaterielService;
import com.ensa.gi4.service.api.GestionAuthentificationService;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ensa.gi4.modele.Role.ADMIN;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    @Qualifier("authentificationService")
    GestionAuthentificationService gestionAuthentificationService;

    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMaterielService;

    @Autowired
    @Qualifier("allocationService")
    GestionAllocationMaterielService gestionAllocationMaterielService;

    public User authentification(List<String> userCredentials) {

        return gestionAuthentificationService.chercherUser(userCredentials);

    }

    public List<String> getUserCredentials() {

        Scanner scanner = new Scanner(System.in);

        String username, password;
        List<String> userCredentials = new ArrayList<String>();

        System.out.println("Pour continuer vous devez vous identifiez");

        System.out.print("Username : ");
        username = scanner.next();

        System.out.print("Password : ");
        password = scanner.next();

        userCredentials.add(username);
        userCredentials.add(password);

        return userCredentials;

    }

    public void afficherMenu(User user) {


        Scanner scanner = new Scanner(System.in);

        switch (user.getRole()) {

            case "admin":
                System.out.println("- pour sortir de l'application, entrer 0");
                System.out.println("- pour lister les matériels, entrer 1");
                System.out.println("- pour chercher un matériel avec son id, entrer 2");


                System.out.println("- pour allouer un materiel, entrez 3");
                System.out.println("- pour rendre un materiel entrer 4");
                System.out.println("- pour afficher la listes de vos materiels, entrez 5");

                System.out.println("- pour ajouter un nouveau materiel, entrer 6");
                System.out.println("- pour supprimer un materiel, entrer 7");


                System.out.println("- pour marquer un materiel comme indisponible, entrez 8");


                System.out.println("- pour modifier un materiel, entrer 9");




                String next = scanner.next();

                if ("0".equals(next)) {

                    sortirDeLApplication();

                } else if ("1".equals(next)) {
                    
                    listerMateriel();

                } else if ("2".equals(next)) {

                    System.out.print("Veuillez entrer l'id du materiel recherché : ");
                    Scanner sc = new Scanner(System.in);
                    long id = Long.parseLong(sc.next());
                    gestionMaterielService.chercherMateriel(id);

                } else if ("3".equals(next)) {

                    System.out.print("Veuillez saisir l'id du materiel que vous souhitez allouer : ");
                    Scanner sc = new Scanner(System.in);
                    long idMateriel = Long.parseLong(sc.next());
                    gestionAllocationMaterielService.allouerMateriel(user, idMateriel);

                } else if ("4".equals(next)) {

                    System.out.print("Veuillez saisir l'id du materiel alloqué que vous souhaitez rendre : ");
                    Scanner sc = new Scanner(System.in);
                    long idAllocation = Long.parseLong(sc.next());
                    gestionAllocationMaterielService.rendreMaterielAlloue(user, idAllocation);

                } else if ("5".equals(next)) {

                    gestionAllocationMaterielService.listerMaterielAllouePourUser(user);

                } else if ("6".equals(next)) {

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




                    materiel.setName(name);
                    materiel.setCode(code);
                    materiel.setType(type);
                    materiel.setStock(stock);
                    materiel.setDispo(dispo);

                    gestionMaterielService.ajouterNouveauMateriel(materiel);

                } else if ("7".equals(next)) {

                    Long id;
                    System.out.print("Veuillez saisir l'id du materiel à supprimer : ");
                    id = scanner.nextLong();
                    gestionMaterielService.supprimerMateriel(id);

                } else if ("8".equals(next)) {

                    Long id;
                    System.out.print("Veuillez saisir l'id du materiel que vous voulez marquer indisponible : ");
                    id = scanner.nextLong();
                    gestionMaterielService.marquerMaterielIndispo(id);

                } else if ("9".equals(next)) {

                    Long id;
                    System.out.print("Veuillez saisir l'id du materiel que vous voulez modifier : ");
                    id = scanner.nextLong();

                    System.out.println("\t\t\t\t\t\t- pour revenir vers le menu principal, tapez a");
                    System.out.println("\t\t\t\t\t\t- pour modifier le nom du materiel, tapez b");
                    System.out.println("\t\t\t\t\t\t- pour modifier le code du materiel, tapez c");
                    System.out.println("\t\t\t\t\t\t- pour modifier le type du materiel, tapez d");
                    System.out.println("\t\t\t\t\t\t- pour modifier le stock du materiel, tapez e");



                    String nouveauNom;
                    String nouveauCode;
                    String nouveauType = null;
                    Long nouveauStock = null;
                    
                    
                    String codeModification;
                    Scanner scannerModification = new Scanner(System.in);
                    codeModification = scannerModification.next();

                    switch (codeModification) {

                        case "a":
                            afficherMenu(user);

                        case "b":
                            
                            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t- Veuillez saisir le nouveau nom du materiel : ");
                            nouveauNom = scannerModification.next();
                            gestionMaterielService.modifierMateriel(id, nouveauNom, nouveauStock, codeModification);


                            break;


                        case "c":
                            
                            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t- Veuillez saisir le nouveau code du materiel : ");
                            nouveauCode = scannerModification.next();
                            gestionMaterielService.modifierMateriel(id, nouveauCode, nouveauStock, codeModification);
                            break;





                        case "d":
                            
                            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t- Veuillez saisir le nouveau type du materiel : ");
                            nouveauType = scannerModification.next();
                            gestionMaterielService.modifierMateriel(id, nouveauType, nouveauStock, codeModification);



                            break;


                        case "e":




                            do {


                                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t- Veuillez saisir le nouveau stock du materiel (il doit être > 0, si vous voulez le mettre à 0 utiliser la fonctionalité  de disponibilité) : ");
                                nouveauStock = scannerModification.nextLong();


                            } while (nouveauStock <= 0);

                            gestionMaterielService.modifierMateriel(id, nouveauType, nouveauStock, codeModification);


                            break;



                    }


                    //gestionMaterielService.marquerMaterielIndispo(id);

                }

                break;


            case "user":
                System.out.println("- pour sortir de l'application, entrez 0");
                System.out.println("- pour afficher la liste des materiels de la bibliothèque, entrez 1");
                System.out.println("- pour chercher un materiel, entrez 2");
                System.out.println("- pour allouer un materiel, entrez 3");
                System.out.println("- pour rendre un materiel entrer 4");
                System.out.println("- pour afficher la listes de vos materiels, entrez 5");


                String next1 = scanner.next();


                if ("0".equals(next1)) {

                    sortirDeLApplication();

                } else if ("1".equals(next1)) {

                    gestionMaterielService.listerMateriel();

                } else if ("2".equals(next1)) {

                    System.out.print("Veuillez entrer l'id du materiel recherché : ");
                    Scanner sc = new Scanner(System.in);
                    long id = Long.parseLong(sc.next());
                    gestionMaterielService.chercherMateriel(id);


                } else if ("3".equals(next1)) {

                    System.out.print("Veuillez saisir l'id du materiel que vous souhitez allouer :");
                    Scanner sc = new Scanner(System.in);
                    long idMateriel = Long.parseLong(sc.next());
                    gestionAllocationMaterielService.allouerMateriel(user, idMateriel);

                } else if ("4".equals(next1)) {

                    System.out.print("Veuillez saisir l'id du materiel alloqué que vous souhaitez rendre : ");
                    Scanner sc = new Scanner(System.in);
                    long idAllocation = Long.parseLong(sc.next());
                    gestionAllocationMaterielService.rendreMaterielAlloue(user, idAllocation);

                }  else if ("5".equals(next1)) {

                    gestionAllocationMaterielService.listerMaterielAllouePourUser(user);

                }

                break;

            default:
                System.out.println("user invalide");
                break;

        }


        /*

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

                }*/


    }

    private void sortirDeLApplication() {

        System.exit(0);

    }

    public void listerMateriel() {

        gestionMaterielService.listerMateriel();

    }

}
