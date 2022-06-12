package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    @Qualifier("materialService")
    GestionMaterielService gestionMaterielService;

    @Autowired
    @Qualifier("userService")
    GestionUserService gestionUserService;

    public void afficherMenu(User user) {
        if("employee".equals(user.getType())){
            System.out.println("1- Chercher un matériel");
            System.out.println("2- lister les matériel");
            System.out.println("3- Allouer un matériel");
            System.out.println("4- Rendre un matériel");  /////prob here ----re service !
            System.out.println("0- pour sortir de l'application");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if ("0".equals(next)) {
                sortirDeLApplication();
            } else if ("1".equals(next)) {
                System.out.print("Entrer un id : ");
                int id = scanner.nextInt();
                gestionMaterielService.chercherrMateriel(id);
            } else if ("2".equals(next)) {
                gestionMaterielService.listerMateriel();
            } else if ("3".equals(next)) {
                System.out.print("Entrer L'id du materiel que vous voulez : ");
                int id = scanner.nextInt();
                System.out.print("Entrer la quantité que vous voulez louer: ");
                int quantity = scanner.nextInt();

               /* Long userID = user.getId();
                int userId = userID.intValue();*/////cant !
                gestionMaterielService.allouerMateriel(id, user, quantity);
            }else if ("4".equals(next)) {
                System.out.print("Entre L'id du materiel que vous voulez retourner : ");
                int id = scanner.nextInt();
                gestionMaterielService.returnMateriel(id, user.getId());
            }
            else {
                System.out.println("choix invalide");
            }
        }
        else if ("admin".equals(user.getType())){
            System.out.println("1 - pour lister les materiels ");
            System.out.println("2 - pour lister les allocations ");
            System.out.println("3 - pour lister les allocations par user ");
            System.out.println("4 - pour ajouter un materiel  ");
            System.out.println("5 - pour supprimer un materiel ");
            System.out.println("6 - pour modifier un materiel ");
            System.out.println("7 - pour chercher un materiel ");
            System.out.println("8- pour changer la disponibilité ");
            System.out.println("0- pour sortir de l'application ");

            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if ("0".equals(next)) {
                sortirDeLApplication();
            } else if ("1".equals(next)) {
                gestionMaterielService.listerMateriel();
            }
            else if ("3".equals(next)) {
              /*  Long userID = user.getId();
                int userId = userID.intValue();*/
                gestionMaterielService.listerAllocationsUser(user.getId());
            }
            else if ("2".equals(next)) {
                gestionMaterielService.listerAllAllocations();
            }
            else if ("4".equals(next)) {
                ajouterNouveauMateriel();
            }
            else if ("5".equals(next)) {
                System.out.print("Entrer un id d'un materiel : ");
                int id = scanner.nextInt();
                gestionMaterielService.supprimerMateriel(id);
            }
            else if ("6".equals(next)) {
                System.out.print("Entrer un id d'un materiel: ");
                int id = scanner.nextInt();
                gestionMaterielService.modifierMateriel(id);
            }
            else if ("7".equals(next)) {
                System.out.print("Entrer un id d'un materiel: ");
                int id = scanner.nextInt();
                gestionMaterielService.chercherrMateriel(id);
            }
           /* else if ("8".equals(next)) {
                Materiel m
                System.out.print("Entre un materiel: ");
                int id = scanner.nextInt();
                gestionMaterielService.editAvailability();
            }*/
            else {
                System.out.println("choix invalide");
            }
        }

    }

    public void loginUser(){
        Scanner scanner = new Scanner(System.in);
        User user = null;
        String type = null;

        while( type == null) {//type
            System.out.println("Enter vos données :");
            System.out.print("\nUsername : ");
            Scanner username = new Scanner(System.in);
            String name = username.next();
            System.out.print("Password : ");
            Scanner password = new Scanner(System.in);
            String psswd = password.next();


            user = gestionUserService.getUser(name, psswd);

            if(user != null)
                type = user.getType();
         //   if( ("admin" != (user.getType()))|| ("employee" != (user.getType()) ) )

           //  System.out.println("\nUser inexistant");
            if (user.getType() == null) {  /////doesnt work ?!

                System.out.println("User non existant ...\n");
                System.out.println("1- pour réessayer ");
                System.out.println("0- pour sortir de l'application ");
                int option = scanner.nextInt();
                if (option == 0) sortirDeLApplication();
                if (option == 1) this.loginUser();
                else  System.out.println("choix invalide");
            }
        }

        System.out.println("Connexion réussie --mode-- " + user.getUsername()+" !");
        while (true) {
            afficherMenu(user);
        }
}

    private void ajouterNouveauMateriel(){
        System.out.println("Taper: 1 pour ajouter un livre , 2 pour ajouter une chaise :");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        if(choix == 1 ) {
            Materiel materiel = new Livre();
            gestionMaterielService.ajouterNouveauMateriel(materiel);
        }else if(choix == 2){
            Materiel materiel = new Chaise();
            gestionMaterielService.ajouterNouveauMateriel(materiel);
        }
        else System.out.println("choix invalide");

    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
