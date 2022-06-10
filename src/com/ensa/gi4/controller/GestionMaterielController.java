package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.UserDao;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMaterielService;

    @Autowired
    @Qualifier("userService")
    GestionUserService gestionUserService;

//    public void afficherMenu() {
//        Scanner scanner  = new Scanner(System.in);
//        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
//    }

    public void authentification() {
        String role = null;
        User user = null;
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        int choice;

        while(role == null) {
            System.out.println("\n*********************************CONNEXION*********************************");
            System.out.print("\nUsername : ");
            username = scanner.next();
            System.out.print("Password : ");
            password = doHashing(scanner.next());

            user = gestionUserService.cherhcerUtilisatuer(username, password);
            if(user != null)
                role = user.getRole();

            if (role == null) {

            System.out.println("\nUsername ou password incorrect");

            System.out.println("\n******************************************");
            System.out.println("1- pour réessayer               :  taper 1");
            System.out.println("0- pour sortir de l'application :  taper 0");
            System.out.println("******************************************\n");

            System.out.print("option : ");
            choice = scanner.nextInt();

            if (choice == 0) sortirDeLApplication();
            else if (choice != 1) System.out.println("choix invalide");
            }
        }

        choice = 0;
        if (role.equals("administrateur , employée")) {
            while(choice != 1 && choice != 2) {
                System.out.println("Voulez-vous se connecter en tant que administrateur (Taper 1) ou employée (Taper 2) : ");
                choice = scanner.nextInt();
                if (choice == 1)
                    role = "administrateur";
                else if (choice == 2)
                    role = "employée";
                else
                    System.out.println("choix invalide");
            }
        }

        System.out.println("\n******************************\nCONNEXION établie avec succès\n******************************");
        System.out.println("\nBIENVENUE " + username);
        while (true) {
            afficherMenu(role, user.getId());
        }
    }

    public void afficherMenu(String role, int userID) {

        if (role.equals("employée")){
            System.out.println("\n******************************************");
            System.out.println("1- pour lister les materiels    :  taper 1");
            System.out.println("2- pour lister les allocations  :  taper 2");
            System.out.println("3- pour chercher un materiel    :  taper 3");
            System.out.println("4- pour allouer un materiel     :  taper 4");
            System.out.println("5- pour rendre un materiel      :  taper 5");
            System.out.println("0- pour sortir de l'application :  taper 0");
            System.out.println("******************************************\n");
            System.out.print("option : ");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if ("0".equals(next)) {
                sortirDeLApplication();
            } else if ("1".equals(next)) {
                listerMateriel();
            } else if ("2".equals(next)) {
                listerAllocations(userID);
            } else if ("3".equals(next)) {
                chercherMateriel();
            } else if ("4".equals(next)) {
                allouerMateriel(userID);
            } else if ("5".equals(next)) {
                rendreMateriel(userID);
            } else {
                System.out.println("choix invalide");
            }
        } else if (role.equals("administrateur") ) {
            System.out.println("\n******************************************");
            System.out.println("1  - pour lister les materiels     :  taper 1");
            System.out.println("2  - pour lister les allocations   :  taper 2");
            System.out.println("3  - pour lister mes allocations   :  taper 3");
            System.out.println("4  - pour ajouter un materiel      :  taper 4");
            System.out.println("5  - pour supprimer un materiel    :  taper 5");
            System.out.println("6  - pour modifier un materiel     :  taper 6");
            System.out.println("7  - pour chercher un materiel     :  taper 7");
            System.out.println("8  - pour allouer un materiel      :  taper 8");
            System.out.println("9  - pour rendre un materiel       :  taper 9");
            System.out.println("10 - pour changer la disponibilité :  taper 10");
            System.out.println("0  - pour sortir de l'application  :  taper 0");
            System.out.println("******************************************\n");
            System.out.print("option : ");
            Scanner scanner = new Scanner(System.in);
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
                listerAllocations(userID);
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
                System.out.println("choix invalide");
            }
        }
    }

    private void changerLaDisponibilite() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre un id : ");
        int id = scanner.nextInt();
        gestionMaterielService.changerDisponibilte(id);
    }

    private void allouerMateriel(int userID) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre un id : ");
        int id = scanner.nextInt();
        System.out.print("Entre la quantité : ");
        int quantity = scanner.nextInt();
        gestionMaterielService.allouerMateriel(id, userID, quantity);
    }

    private void rendreMateriel(int userID) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre un id : ");
        int id = scanner.nextInt();
        System.out.print("Entre la quantité : ");
        int quantity = scanner.nextInt();
        gestionMaterielService.rendreMateriel(id, userID, quantity);
    }

    private void listerAllocations(int userID) {
        gestionMaterielService.affihcerMaterielAllouerParUtilisateur(userID);
    }

    private void listerAllocations() {
        gestionMaterielService.listerAllocations();
    }

    private void modifierMateriel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre un id : ");
        int ID = scanner.nextInt();
        gestionMaterielService.modifierMateriel(ID);
    }

    private void supprimerMateriel() {
        System.out.print("Entre un id : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        gestionMaterielService.supprimerMateriel(id);
    }

    private void ajouterMateriel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tapez 1 pour ajouter un livre \nTapez 2 pour ajouter une chaise ");
        System.out.print("\t Choix : ");
        int type = scanner.nextInt();
        System.out.println("\nRemplissez les données demandées: ");
        if(type == 1 ) {
            Materiel livre = new Livre();
            gestionMaterielService.ajouterNouveauMateriel(livre);
        }else if(type == 2){
            Materiel chaise = new Chaise();
            gestionMaterielService.ajouterNouveauMateriel(chaise);
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

    private void listerMateriel(){
        gestionMaterielService.listerMateriel();
    }

    private void chercherMateriel(){
        System.out.print("Entre un id : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        gestionMaterielService.chercherMaterielID(id);
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
