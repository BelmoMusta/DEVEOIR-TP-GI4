package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class GestionUserImp implements GestionUser {
    @Autowired
    UserDaoImpl userDao;
    @Autowired
            GestionMaterielServiceImpl  gestionMaterielService;
    String roles;
    @Override
    public void userLogin() {
int i=0;
while (i==0) {

    System.out.println("your NAME : ");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();
    System.out.println("your Password : ");
    String pass = scanner.next();
    User user = new User();
    user.setName(name);
    user.setPassword(pass);

    if (userDao.findUser(user).isEmpty()) {
        System.out.println("name or password incorrect ");
        System.out.println("taper 0 pour quiter l'app ");
        Scanner t = new Scanner(System.in);
        if ("0".equals(t)) {
             gestionMaterielService.sortirDeLApplication();
        }
    }

    if (!userDao.findUser(user).isEmpty()) {
        i=1;
        roles = userDao.findUser(user).get(0).getRole();
        System.out.println(roles);
    }
    if ("ADMIN".equals(roles)) {
        i=1;
        System.out.println("Hello Admin");
        while (true) {
            adminMunu();
        }
    } else if ("USER".equals(roles)) {
        i=1;
        System.out.println("HERE IS THE MENU");
        while (true) {
            userMunu();
        }
    }

}}

    private void userMunu() {
        System.out.println("0- pour sortir de l'application, entrer 0");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour lister vos materiel alloue, entrer 2");
        System.out.println("3- pour alloer un  matériel par code, entrer 3");
        System.out.println("4- pour chercher un  matériel par id, entrer 4");
        System.out.println("5- pour rendre un  matériel , entrer 5");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            gestionMaterielService.sortirDeLApplication();
        } else if ("1".equals(next)) {
           gestionMaterielService.listerMateriel();
        } else if ("2".equals(next)) {
         gestionMaterielService.listerMatAllouer();
        } else if ("3".equals(next)) {
            gestionMaterielService.allouerMateriel();
        }
        else if ("4".equals(next)) {
            gestionMaterielService.search();
        } else if ("5".equals(next)) {
            gestionMaterielService.renderMateriel();
        } else{
            System.out.println("choix invalide");
        }

    }



    private void adminMunu() {
        System.out.println("0- pour sortir de l'application, entrer 0");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour supprimer un  matériel par id, entrer 3");
        System.out.println("4- pour chercher un  matériel par id, entrer 4");
        System.out.println("5- pour modifier un  matériel , entrer 5");
        System.out.println("6- pour allouer un  matériel , entrer 6");
        System.out.println("7- pour marquer un materiel dispo ou non  , entrer 7");
        System.out.println("8- pour render un materiel , entrer 8");
        System.out.println("9- pour un materil epuise , entrer 9");
        System.out.println("10- pour lister le matériel alloue par chaque user , entrer 10");
         Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            gestionMaterielService.sortirDeLApplication();
        } else if ("1".equals(next)) {
            gestionMaterielService.listerMateriel();
        } else if ("2".equals(next)) {
            gestionMaterielService.ajouterNouveauMateriel();
        } else if ("3".equals(next)) {
           gestionMaterielService.delet();
        }
        else if ("4".equals(next)) {
           gestionMaterielService.search();
        } else if ("5".equals(next)) {
            gestionMaterielService.update();
        }else if ("6".equals(next)) {
            gestionMaterielService.allouerMateriel();
        }else if ("7".equals(next)) {
        gestionMaterielService.marquerIndispoouDispo();
        }else if ("8".equals(next)) {
            gestionMaterielService.renderMateriel();
        }else if ("9".equals(next)) {
            gestionMaterielService.materielEpuise();
        }else if ("10".equals(next)) {
            gestionMaterielService.listerMateriel();
        } else{
            System.out.println("choix invalide");
        }

    }

    }
