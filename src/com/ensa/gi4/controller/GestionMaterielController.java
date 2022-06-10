package com.ensa.gi4.controller;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMaterielService;

    @Autowired
    @Qualifier("userService")
    GestionUserService gestionUserService;

    public void authentification() {
        Scanner scanner = new Scanner(System.in);
        String role;
        User user;
        String username;
        String password;
        String choice;

        while(true) {
            System.out.print("\nNom d'utilisateur : ");
            username = scanner.next();
            System.out.print("Mot de pass : ");
            password = doHashing(scanner.next());

            user = gestionUserService.cherhcerUtilisatuer(username, password);

            if(user != null) {
                role = user.getRole();
                break;
            }

            System.out.println("\nOups! Nom d'utilisateur ou mot de pass incorrect\n");

            while (true) {
                System.out.println("--------------------------------------");
                System.out.println("Tapez 1 pour : réessayer à nouveau");
                System.out.println("Tapez 0 pour : sortir de l'application");
                System.out.println("--------------------------------------\n");

                System.out.print("option : ");
                choice = scanner.next();

                if ("0".equals(choice))
                    sortirDeLApplication();
                else if ("1".equals(choice))
                    break;
                else
                    System.out.println("\n'" + choice + "' est un choix invalide, tapez '1' ou '0'\n");
            }
        }

        if (role.equals("admin , emp")) {
            while(true) {
                System.out.println("\n--------------------------------------------------------");
                System.out.println("Tapez 1 pour : se connecter en tant qu'un administrateur");
                System.out.println("Tapez 2 pour : se connecter en tant qu'un employé");
                System.out.println("------------------------------------------------------\n");

                System.out.print("option : ");
                choice = scanner.next();

                if ("1".equals(choice)) {
                    role = "admin";
                    break;
                }
                else if ("2".equals(choice)) {
                    role = "emp";
                    break;
                }
                else
                    System.out.println("\n'" + choice + "' est un choix invalide, tapez '2' ou '1'");
            }
        }

        while (true) {
            afficherMenu(role, user.getId());
        }
    }

    public void afficherMenu(String role, int userID) {

        if (role.equals("emp")){
            System.out.println("\n--------------------------------------");
            System.out.println("--------------------------------------");
            System.out.println("Tapez 1 pour : lister les materiels");
            System.out.println("Tapez 2 pour : lister vos allocations");
            System.out.println("Tapez 3 pour : chercher un materiel");
            System.out.println("Tapez 4 pour : allouer un materiel");
            System.out.println("Tapez 5 pour : rendre un materiel");
            System.out.println("Tapez 0 pour : sortir de l'application");
            System.out.println("--------------------------------------");
            System.out.println("--------------------------------------\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("option : ");
            String next = scanner.next();

            if ("0".equals(next)) {
                sortirDeLApplication();
            } else if ("1".equals(next)) {
                listerMateriel();
            } else if ("2".equals(next)) {
                listerVosAllocations(userID);
            } else if ("3".equals(next)) {
                chercherMateriel();
            } else if ("4".equals(next)) {
                allouerMateriel(userID);
            } else if ("5".equals(next)) {
                rendreMateriel(userID);
            } else {
                System.out.println("\n'" + next + "' est un choix invalide, réessayez");
            }
        } else if (role.equals("admin") ) {
            System.out.println("\n-------------------------------------------");
            System.out.println("-------------------------------------------");
            System.out.println("Tapez 1  pour : lister les materiels");
            System.out.println("Tapez 2  pour : lister les allocations");
            System.out.println("Tapez 3  pour : lister vos allocations");
            System.out.println("Tapez 4  pour : ajouter un materiel");
            System.out.println("Tapez 5  pour : supprimer un materiel");
            System.out.println("Tapez 6  pour : modifier un materiel");
            System.out.println("Tapez 7  pour : chercher un materiel");
            System.out.println("Tapez 8  pour : allouer un materiel");
            System.out.println("Tapez 9  pour : rendre un materiel");
            System.out.println("Tapez 10 pour : changer la disponibilité");
            System.out.println("Tapez 0  pour : sortir de l'application");
            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("option : ");
            String next = scanner.next();

            if ("0".equals(next)) {
                sortirDeLApplication();
            } else if ("1".equals(next)) {
                listerMateriel();
            }
            else if ("2".equals(next)) {
                listerAllocations();
            }
            else if ("3".equals(next)) {
                listerVosAllocations(userID);
            }
            else if ("4".equals(next)) {
                ajouterMateriel();
            }
            else if ("5".equals(next)) {
                supprimerMateriel();
            }
            else if ("6".equals(next)) {
                modifierMateriel();
            }
            else if ("7".equals(next)) {
                chercherMateriel();
            }
            else if ("8".equals(next)) {
                allouerMateriel(userID);
            }
            else if ("9".equals(next)) {
                rendreMateriel(userID);
            }
            else if ("10".equals(next)) {
                changerLaDisponibilite();
            }
            else {
                System.out.println("\n'" + next + "' est un choix invalide, réessayez");
            }
        }
    }

    private void listerMateriel(){
        gestionMaterielService.listerMateriel();
    }

    private void listerAllocations() {
        gestionMaterielService.listerAllocations();
    }

    private void listerVosAllocations(int userID) {
        gestionMaterielService.listerAllocationsParUser(userID);
    }

    private void ajouterMateriel() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        Materiel materiel;

        while(true) {
            System.out.println("\n---------------------------------");
            System.out.println("Tapez 1 pour : ajouter un livre");
            System.out.println("Tapez 2 pour : ajouter une chaise");
            System.out.println("---------------------------------\n");

            System.out.print("option : ");
            choice = scanner.next();

            if ("1".equals(choice)) {
                materiel = new Livre();
                gestionMaterielService.ajouterNouveauMateriel(materiel);
                break;
            } else if ("2".equals(choice)) {
                materiel = new Chaise();
                gestionMaterielService.ajouterNouveauMateriel(materiel);
                break;
            } else
                System.out.println("\n'" + choice + "' est un choix invalide, tapez '1' ou '2'");
        }
    }

    private void supprimerMateriel() {
        System.out.println("\nLa liste des materiels est : ");
        gestionMaterielService.listerMateriel();
        System.out.print("\nEntrer le nom du materiel à supprimer : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.supprimerMateriel(nom);
    }

    private void modifierMateriel() {
        System.out.println("\nLa liste des materiels est : ");
        gestionMaterielService.listerMateriel();
        System.out.print("\nEntrer le nom du materiel à modifier : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.modifierMateriel(nom);
    }

    private void chercherMateriel(){
        System.out.print("\nEntrer le nom du materiel à chercher : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.chercherMateriel(nom);
    }

    private void allouerMateriel(int userID) {
        System.out.print("\nEntre le nom du materiel à allouer : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.allouerMateriel(nom, userID);
    }

    private void rendreMateriel(int userID) {
        System.out.print("\nEntre le nom du materiel à rendre : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.rendreMateriel(nom, userID);
    }

    private void changerLaDisponibilite() {
        System.out.print("\nEntrer le nom du materiel : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.next();
        gestionMaterielService.changerDisponibilte(nom);
    }

    private void sortirDeLApplication() {
        System.out.println("\n--------------------------");
        System.out.println("Merci pour votre visite :)");
        System.out.println("--------------------------");
        System.exit(0);
    }

    public String doHashing(String password)
    {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : resultByteArray)
            {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
