package com.ensa.controller;

import com.ensa.dao.AllocationDao;
import com.ensa.modele.Allocation;
import com.ensa.modele.User;
import com.ensa.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Controller {

    MaterielController livre;
    MaterielController chaise;
    @Autowired
    UserService userservice;

    @Autowired
    @Qualifier("livrecont")
    public void setLivreMaterielController(MaterielController livre) {
        this.livre = livre;
    }

    @Autowired
    @Qualifier("chaisecont")
    public void setGestionChaiseController(MaterielController chaise) {
        this.chaise = chaise;
    }
    @Autowired
    private AllocationDao allo;

    public void affichermenu() {
        User user = userservice.login();
        if (user != null) {
Boolean f=true;
            while (f) {
                System.out.println("0- pour se deconnecter 0");
                System.out.println("1- pour choisir les Chaises entrer 1");
                System.out.println("2- pour choisir les Livres 2");
                System.out.println("3- Afficher la liste des matériels alloués par moi 3");
                if(user.getIsadmin()){
                System.out.println("4- pour afficher la liste des matériels alloués par chaque utilisateur");}




                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if ("1".equals(next)) {
                    chaise.afficherMenu(user);

                } else if ("2".equals(next)) {
                    livre.afficherMenu(user);

                }
                else if ("3".equals(next)) {
                    for(  Allocation a:allo.findbyCodeList(user.getUsername()))  {System.out.println(a.getProductid());}

                }
                else if ("0".equals(next)) {
                    f=false;

                }
                else if ("4".equals(next)&& user.getIsadmin()) {
                    for(  Allocation a:allo.findAll())  {
                        System.out.print(a.getUserid()+":"+ " Code du produit :"+a.getProductid()+"  Date:"+a.getAllocationdate()+"\n");}
                }
                else {
                    System.out.println("choix invalide");

                }
            }
        }else System.out.println("Identifiants incorrects ");
    }
}


