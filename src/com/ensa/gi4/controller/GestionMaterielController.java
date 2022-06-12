package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.personne;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonService;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;
    @Autowired
    @Qualifier("materielService")
    GestionMaterielService materiel;

    @Autowired
    GestionPersonService connexion;

    public void afficherMenu(){
        System.out.println("--------------------------- Bienvenu -----------------------------");
        System.out.println("1- Pour se connecter, entrer 1");
        System.out.println("0- Pour sortir de l'application, entrer 0");
        Scanner clavier = new Scanner(System.in);
        String rep = clavier.next();
        if("0".equals(rep)){
            sortirDeLApplication();
        } else if ("1".equals(rep)) {
            try{
                personne p =  connexion.signInPerson();
                if("admin".equals(p.getRole())){
                    System.out.println("Bonjour "+ p.getName());
                    afficherMenuAdmin((long) p.getId(),p.getName());
                }else {
                    System.out.println("Bonjour "+ p.getName());
                    afficherMenuUser((long) p.getId(),p.getName());
                }
            }catch (NullPointerException e){
                System.out.println("Utilisateur n'existe pas");
            }


        }else{
            System.out.println("Numero invalide");
        }

    }

    public void afficherMenuUser(Long idUser, String nomUser) {
        while (true) {
            System.out.println("1- pour lister materiels, entrer 1");
            System.out.println("2- pour recuperer les informations d'un materiel, entrer 2");
            System.out.println("3- pour allouer un materiel, entrer 3");
            System.out.println("4- pour rendre un materiels, entrer 4");
            System.out.println("5- pour lister materiels allouées par vous, entrer 5");
            System.out.println("0- pour sortir de l'application, entrer 0");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if ("0".equals(next)) {
                sortirDeLApplication();
                break;

            } else if ("1".equals(next)) {
                materiel.listerMaterielUser();
            } else if ("2".equals(next)) {
                materiel.chercherMateriel();
            } else if ("3".equals(next)){
                materiel.allouerMateriel(idUser,nomUser);
            }else if ("4".equals(next)){
                materiel.rendreMateriel(idUser);
            }else if ("5".equals(next)){
                materiel.afficherListeMaterielsAllouesParuser(idUser);
            }
        }
    }

    public void afficherMenuAdmin(Long idUser, String nomUser) {
        while(true){
        System.out.println("1- pour recuperer la liste des materiels, entrer 1");
        System.out.println("2- pour recuperer les informations d'un materiel, entrer 2");
        System.out.println("3- pour ajouter un nouveau materiel, entrer 3");
        System.out.println("4- pour supprimer un materiel, entrer 4");
        System.out.println("5- pour modifier un materiel, entrer 5");
        System.out.println("6- pour marquer un materiel indisponible, entrer 6");
        System.out.println("7- pour allouer un materiel, entrer 7");
        System.out.println("8- pour rendre un materiel, entrer 8");
        System.out.println("9- pour lister materiels alloues par un utilisateur, entrer 9");
        System.out.println("10- pour lister materiels alloues, entrer 10");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
            break;

        } else if ("1".equals(next)) {
           materiel.listerMateriel();
        } else if ("2".equals(next)) {
              materiel.chercherMateriel();
        } else if ("3".equals(next)) {
              materiel.ajouterNouveauMateriel();
        } else if ("4".equals(next)) {
            materiel.supprimerMateriel();
        }
        else if ("5".equals(next)) {
           materiel.modifierMateriel();
        }
        else if ("6".equals(next)) {
              materiel.marquerIndisponible();
        }
        else if ("7".equals(next)) {
            materiel.allouerMateriel(idUser,nomUser);
        }else if("8".equals(next)){
            materiel.rendreMateriel(idUser);
        }else if("9".equals(next)){
            materiel.afficherListeMaterielsAllouesParuser(idUser);
        }else if("10".equals(next)){
            materiel.afficherListeMaterielsAlloues();
        }

    }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
