package com.ensa.gi4.controller;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    GestionMaterielService materielService;

    @Autowired
    UserDao userDao;

    String role;

    public void login() {
        int i=0;
        while(i==0){
            System.out.println("Welcome to our application .. ");
            System.out.println("User Name : ");
            Scanner sc1 = new Scanner(System.in);
            String  userName = sc1 .next();
            System.out.println("Password :");
            Scanner sc2 = new Scanner(System.in);
            String password = sc2.next();
            User user =new User();
            user.setUserName(userName);
            user.setPassword(password);

            if(userDao.login(user).isEmpty()){
                System.out.println("The username or password is incorrect");
                System.out.println("enter 0 to quit the application or enter any number to re-enter your information");
                Scanner sc3 = new Scanner(System.in);
                String touche = sc3.next();
                if ("0".equals(touche)) {
                    sortirDeLApplication();
                }
            }

            if(!userDao.login(user).isEmpty()){
                i=1;
                role=userDao.login(user).get(0).getRole();
            }

            if("ADMIN".equals(role)){
                i=1;
                while(true){
                    afficherAdminMenu();
                }
            }

            else if("USER".equals(role)){
                i=1;
                while(true){
                    afficherUserMenu();
                }
            }
        }}

    public void afficherAdminMenu() {
        System.out.println("1- Search for a material");
        System.out.println("2- Create a new material;");
        System.out.println("3- Delete a material");
        System.out.println("4- Update informations of a material");
        System.out.println("5- Mark material unavailable or available");
        System.out.println("6- Allocate material");
        System.out.println("7- Return a material");
        System.out.println("8- Display the list of materials allocated by you");
        System.out.println("9- Display the list of materials allocated by each user");
        System.out.println("10- Display the list of materials ");
        System.out.println("0- quit the application");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            materielService.chercherMateriel();
        } else if ("2".equals(next)) {
            materielService.ajouterNouveauMateriel();
        } else if ("3".equals(next)) {
            materielService.supprimerMateriel();
        }
        else if ("4".equals(next)) {
            materielService.modifierMateriel();
        } else if ("5".equals(next)) {
            materielService.marquerIndisponible();
        }else if ("6".equals(next)) {
            materielService.allouerMateriel();
        } else if ("7".equals(next)) {
            materielService.rendreMateriel();
        } else if ("8".equals(next)) {
            List<Materiel> list = materielService.afficherMaterielLuiMeme();
            for(Materiel i : list){
                System.out.println(" le " + i.getName()  + " " + i.getCode());
            }
        } else if ("9".equals(next)) {
            List<Materiel> list = materielService.afficherMaterielPourChaqueUser();
            for(Materiel i : list){
                System.out.println(i.getName()  + "  : " + i.getCode());
            }        }
        else if ("10".equals(next)) {
            List<Materiel> list = materielService.afficherMateriel();
            for(Materiel i : list){
             System.out.println(i.getName()  + "  : " + i.getCode());
            }
        } else{
            System.out.println("invalid choice");
        }
    }

    public void afficherUserMenu() {

        System.out.println("1- Search for a material");
        System.out.println("2- Allocate a material");
        System.out.println("3- Return a material");
        System.out.println("4- Display the list of materials allocated by you");
        System.out.println("5- Display the list of all materials");
        System.out.println("0- quit the application");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            materielService.chercherMateriel();
        } else if ("2".equals(next)) {
            materielService.allouerMateriel();
        } else if ("3".equals(next)) {
            materielService.rendreMateriel();
        }
        else if ("4".equals(next)) {
            materielService.afficherMaterielLuiMeme();
        } else if ("5".equals(next)) {
            List<Materiel> list = materielService.afficherMateriel();
            for(Materiel i : list){
                System.out.println(i.getName()  + "  : " + i.getCode());
            }
        }
        else{
            System.out.println("invalid choice");
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }
}
