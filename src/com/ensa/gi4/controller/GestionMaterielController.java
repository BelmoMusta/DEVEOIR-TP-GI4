package com.ensa.gi4.controller;

import com.ensa.gi4.appuser.AppUser;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    GestionMaterielService materielDao;

    @Autowired
    UserDao userDao;

    String role;

    public void login() {
       int i=0;
       while(i==0){
        System.out.println("1- Entrer votre nom:");
        Scanner nom = new Scanner(System.in);
        String noma = nom.next();
        System.out.println("2- Entrer votre mot de passe");
        Scanner motDePasse = new Scanner(System.in);
        String motDePassea = nom.next();
        AppUser user =new AppUser();
        user.setPassword(motDePassea);
        user.setUserName(noma);

        if(userDao.loginUser(user).isEmpty()){
            System.out.println("donnees incorrectes, " +
                    "saisir 0 pour sortir de l'appication ou une touche quelconque pour ressaisir votre informations");
            Scanner touche = new Scanner(System.in);
            String touchea = nom.next();
            if ("0".equals(touchea)) {
                sortirDeLApplication();
            }
        }

           if(!userDao.loginUser(user).isEmpty()){
               i=1;
               role=userDao.loginUser(user).get(0).getAppUserRole();
           }
         if("ADMIN".equals(role)){
            i=1;
            while(true){
                afficherAdminMenu();
            }

        }
            else if("Employée".equals(role)){
            i=1;
             while(true){
                 afficherEmployeeMenu();
             }

        }

    }}

    public void afficherAdminMenu() {

        System.out.println("1- Chercher un matériel");
        System.out.println("2- Créer un nouveau matériel;");
        System.out.println("3- Supprimer un matériel");
        System.out.println("4- Modifier les informations d'un matériel");
        System.out.println("5- Marquer un matériel indisponible");
        System.out.println("6- Allouer un matériel");
        System.out.println("7- Rendre un matériel");
        System.out.println("8- Afficher la liste des matériels alloués par lui même");
        System.out.println("9- Afficher la liste des matériels alloués par chaque utilisateur");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            materielDao.chercherMateriel();
        } else if ("2".equals(next)) {
            System.out.println("r");
        } else if ("3".equals(next)) {
            System.out.println("r");
        }
        else if ("4".equals(next)) {
            System.out.println("r");
        } else if ("5".equals(next)) {
            System.out.println("r");
        }else if ("6".equals(next)) {
            System.out.println("r");
        } else{
            System.out.println("choix invalide");
        }
    }
    public void afficherEmployeeMenu() {

        System.out.println("1- Chercher un matériel");
        System.out.println("2- Allouer un matériel");
        System.out.println("3- Rendre un matériel");
        System.out.println("4- Afficher la liste des matériels alloués par lui même");
        System.out.println("5- Afficher la liste de tous les matériels");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            System.out.println("r");
        } else if ("2".equals(next)) {
            System.out.println("r");
        } else if ("3".equals(next)) {
            System.out.println("r");
        }
        else if ("4".equals(next)) {
            System.out.println("r");
        } else if ("5".equals(next)) {
            System.out.println("r");
        }else{
            System.out.println("choix invalide");
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
