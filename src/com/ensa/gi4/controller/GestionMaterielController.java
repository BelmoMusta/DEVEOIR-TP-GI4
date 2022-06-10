package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.impl.UserDaoImp;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.impl.GestionMaterielAllocatedServiceImpl;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.GestionUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component("controllerPrincipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    @Autowired
    private GestionMaterielServiceImpl gestionMaterielService;

    @Autowired
    private GestionMaterielAllocatedServiceImpl gestionMaterielAllocatedService;

    @Autowired
    private GestionUserServiceImp gestionUserService;


    private User user ;
    public void saisiInfo(Materiel nouveau){

        System.out.println("Le nom : ");
        Scanner sc2 = new Scanner(System.in);
        String nom = sc2.nextLine();
        nouveau.setMaterielName(nom);

        System.out.println("Le code : ");
        Scanner sc3 = new Scanner(System.in);
        String code = sc3.next();
        nouveau.setMaterielCode(code);

        gestionMaterielService.ajouterMateriel(nouveau);
    }
    public void menuPourTous(){
        System.out.println("0- pour sortir de l'application, entrer 0");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour chercher materiel par id, entrer 2");
        System.out.println("3- pour allouer un materiel, entrer 3");
        System.out.println("4- pour rendre un materiel, entrer 4");
        System.out.println("5- pour lister vos materiels alloués, entrer 5");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            gestionMaterielService.listerMateriel();
        } else if ("2".equals(next)) {
            System.out.println("Entrez le numero du matériel à rechercher : ");
            Scanner scanner1 = new Scanner(System.in);
            long numero = Long.parseLong(scanner1.next());
            gestionMaterielService.chercherMateriel(numero);
        } else if ("3".equals(next)) {
            System.out.println("Entrer le nom du matériel à allouer : ");
            Scanner scanner1 = new Scanner(System.in);
            String nom = scanner1.nextLine();

            System.out.println("Entrer la quantité à allouer : ");
            Scanner scanner2 = new Scanner(System.in);
            int qte = scanner2.nextInt();

            System.out.println("Entrer le nombre de jours d'allocation : ");
            Scanner scanner3 = new Scanner(System.in);
            int days = scanner3.nextInt();
           gestionUserService.allouer(user.getIdUser(), nom,qte,days);
        }else if ("4".equals(next)) {
            System.out.println("Entrer le nom du matériel à rendre : ");
            Scanner scanner1 = new Scanner(System.in);
            String nom = scanner1.nextLine();
            gestionUserService.rendre(nom);
        }else if ("5".equals(next)) {
            gestionMaterielAllocatedService.listerMesMaterielAllouees(user.getIdUser());
        }
       else {
            System.out.println("choix invalide");
        }
    }
    public void afficherMenu() {
        System.out.println("1- pour ajouter materiel, entrer 1");
        System.out.println("2- pour supprimer materiel, entrer 2");
        System.out.println("3- pour modifier materiel, entrer 3");
        System.out.println("4- pour rendre materiel indisponible, entrer 4");
        System.out.println("5- pour lister les materiels alloués, entrer 5");
        System.out.println("6- pour revenir au menu pour tous, entrer 6");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
          if ("1".equals(next)) {
              System.out.println("1-Tapez 1 pour ajouter un livre");
              System.out.println("2-Tapez 2 pour ajouter une chaise");
              Scanner scanTyp = new Scanner(System.in);
              int typeMat = scanTyp.nextInt();
              if(typeMat == 1 ) {
                  Materiel nouveau = new Livre();
                  saisiInfo(nouveau);
              }else if(typeMat == 2){
                  Materiel nouveau = new Chaise();
                  saisiInfo(nouveau);
              }
        }else if ("2".equals(next)) {
            System.out.println("Entrez le numero du matériel à supprimer : ");
            Scanner scanner1 = new Scanner(System.in);
            int numsupp = scanner1.nextInt();
            gestionMaterielService.supprimerMateriel(numsupp);
        } else if ("3".equals(next)) {
            System.out.println("Entrez le numero du matériel à modifier : ");
            Scanner scanner1 = new Scanner(System.in);
            int numod = scanner1.nextInt();
            gestionMaterielService.modifierMateriel(numod);
        }else if ("4".equals(next)) {
            System.out.println("Entrez le numero du matériel à rendre indisponible : ");
            Scanner scanner1 = new Scanner(System.in);
            int numdispo = scanner1.nextInt();
            gestionMaterielService.rendreIndispo(numdispo);
        }else if("5".equals(next)){
          gestionMaterielAllocatedService.listerMaterielAllouees();
          }else if ("6".equals(next)) {
            menuPourTous();
        }else if ("0".equals(next)) {
              sortirDeLApplication();
          } else {
            System.out.println("choix invalide");
        }
    }
    public void identifier(){
        System.out.println("\n----------- Bienvenue du nouveau -----------\n");
        System.out.println("Username :");
        Scanner scanner1 = new Scanner(System.in);
        String usermane = scanner1.nextLine();
        System.out.println("Password :");
        Scanner scanner2 = new Scanner(System.in);
        String password = scanner2.nextLine();
        String passwordHashed = doHashing(password);
        user = gestionUserService.login(usermane,passwordHashed);
        if(user.getRole().equals("admin")) {
            while (true){
                System.out.println("pour afficher menu general tapez 0 pour afficher menu spécifié tapez 1 :");
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if ("1".equals(next)) {
                    afficherMenu();
                }else if("0".equals(next)) {
                    menuPourTous();
                }
            }
        }
        else{
            while (true){
                menuPourTous();
            }
        }
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
    public void inscrire(){
        System.out.println("Username :");
        Scanner scanner1 = new Scanner(System.in);
        String usermane = scanner1.nextLine();
        System.out.println("Password :");
        Scanner scanner2 = new Scanner(System.in);
        String password = scanner2.nextLine();
        String passwordHashed = doHashing(password);

        gestionUserService.register(usermane,passwordHashed);
    }

    public void signUpOrSignIn(){
        System.out.println("NB:pour se connecter en admin entrez admin comme username et 1234 comme password");
        System.out.println("1- pour s'inscrire , entrer 1");
        System.out.println("2- pour s'identifier , entrer 2");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("1".equals(next)) {
            inscrire();
            identifier();
        }else if ("2".equals(next)) {
            identifier();
        }
        else {
            System.out.println("choix invalide");
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
