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


import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService materielService;

    @Autowired
    @Qualifier("userService")
    private GestionUserService userService;

    public void ajouterNouveauMateriel(Materiel materiel) {
        materielService.ajouterNouveauMateriel(materiel);
    }
    public void allouerMateriel(Materiel m,User u) {
        materielService.allouerMateriel(m,u);
    }

    public void listerMateriels() {
        materielService.listerMateriels();
    }
    public void listerMaterielsAlloues() {
        materielService.listerMaterielsAlloues();
    }
    public void listerMaterielsAllouesParUser(String username) {
        materielService.listerMaterielsAllouesParUser(username);
    }

    public void modifierMateriel(Materiel materiel,String nouveauCode) {
        materielService.modifierMateriel(materiel,nouveauCode);
    }
    public void RechercheMateriel(Materiel materiel) {
        materielService.rechercheMateriel(materiel);
    }
    public void RendreMateriel(Materiel materiel,String username) {
        materielService.RendreMateriel(materiel,username);
    }
    public void supprimerMateriel(Materiel materiel) {
        materielService.supprimerMateriel(materiel);

    }

    public Boolean trouverUser(User user) {
        return userService.trouverUser(user);
    }
    public Boolean trouverRole(User user, String role) {
        return userService.trouverRole(user, role);
    }


    static private String username;
    static private String password;

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer votre nom :");
        username = sc.nextLine();
        System.out.println("Entrer votre mot de passe :");
        password = sc.nextLine();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            Boolean result=trouverUser(user);
            if(result==true) {
                if(trouverRole(user,"ADMIN")) {
                    System.out.println("vous etes bien authentifiée !!! \n ");
                    while (true) {
                        afficherMenuAdmin();}
                }else if(trouverRole(user,"USER")) {
                   while (true) {
                        afficherMenuUser();}}
            }
        }catch (NullPointerException e){
            System.out.println("authentification échouée !!!!");

        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

    public void afficherMenuUser() {
        System.out.println("Voici le menu de l'utilisateur : \n");
        System.out.println("00- pour sortir de l'application, entrer O0");
        System.out.println("0- pour se deconnecter, entrer 0");
        System.out.println("1- pour lister tous les materiels, entrer 1");
        System.out.println("2- pour lister les materiels que j'ai alloué, entrer 2");
        System.out.println("3- pour allouer un materiel, entrer 3");
        System.out.println("4- pour rendre un materiel, entrer 4");
        System.out.println("5- pour chercher un materiel, entrer 5");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("00".equals(next)) {
            sortirDeLApplication();
        }else if ("0".equals(next)) {
            login();
        } else if ("1".equals(next)) {
            listerMateriels();
        } else if("2".equals(next)) {
            listerMaterielsAllouesParUser(username);
        }
        else if ("3".equals(next)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pour allouer un livre tapez 1 ");
            System.out.println("pour allouer une chaise tapez 2 ");
            String nx = sc.nextLine();
            User user = new User();
            user.setUsername(username);
            if("1".equals(nx)) {
                Materiel livre = new Livre();
                livre.setName("Livre");
                System.out.println("donner le code du livre:");
                String code = sc.nextLine();
                livre.setCode(code);
                allouerMateriel(livre,user);
            }else if("2".equals(nx)) {
                Materiel chaise = new Chaise();
                chaise.setName("Chaise");
                System.out.println("donner le code de la chaise :");
                String code = sc.nextLine();
                chaise.setCode(code);
                allouerMateriel(chaise,user);
            }
        }else if ("4".equals(next)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pour rendre un livre tapez 1 ");
            System.out.println("pour rendre une chaise tapez 2 ");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreARendre = new Livre();
                livreARendre.setName("Livre");
                System.out.println("Donner le code du Livre a rendre");
                String codLivre = sc.nextLine();
                livreARendre.setCode(codLivre);
                RendreMateriel(livreARendre,username);
            }else if("2".equals(nx)) {
                Materiel chaiseARendre = new Chaise();
                chaiseARendre.setName("Chaise");
                System.out.println("Donner le code de la Chaise a rendre :");
                String codeChaise = sc.nextLine();
                chaiseARendre.setCode(codeChaise);
                RendreMateriel(chaiseARendre,username);
            }
        }
        else if("5".equals(next)){
            Scanner sc = new Scanner(System.in);
            System.out.println("pour chercher un livre tapez 1 :");
            System.out.println("pour chercher une chaise tapez 2 :");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreRecherche = new Livre();
                System.out.println("Donner le code du Livre que vous cherchez :");
                String codeLivre = sc.nextLine();
                livreRecherche.setCode(codeLivre);
                RechercheMateriel(livreRecherche);
            }else if("2".equals(nx)) {
                Materiel chaiseRecherche = new Chaise();
                System.out.println("Donner le code du Livre que vous cherchez :");
                String codeLivre = sc.nextLine();
                chaiseRecherche.setCode(codeLivre);
                RechercheMateriel(chaiseRecherche);
            }
        }
        else {
            System.out.println("choix invalide");
        }
    }

    public void afficherMenuAdmin() {
        System.out.println("Voici le menu de l'admin : \n");
        System.out.println("00- pour sortir de l'application, entrer O0");
        System.out.println("0- pour se deconnecter, entrer 0");
        System.out.println("1- pour lister tous les materiels, entrer 1");
        System.out.println("2- pour lister les materiels que j'ai alloué , entrer 2");
        System.out.println("3- pour lister tous les materiels alloues, entrer 3");
        System.out.println("4- pour ajouter un nouveau materiel, entrer 4");
        System.out.println("5- pour modifier un materiel, entrer 5");
        System.out.println("6- pour supprimer un materiel, entrer 6");
        System.out.println("7- pour chercher un materiel, entrer 7");
        System.out.println("8- pour allouer un materiel, entrer 8");
        System.out.println("9- pour rendre un materiel, entrer 9");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("00".equals(next)) {
            sortirDeLApplication();
        }
        else if ("0".equals(next)) {
            login();
        } else if ("1".equals(next)) {
            listerMateriels();
        } else if("2".equals(next)) {
            listerMaterielsAllouesParUser(username);

        } else if("3".equals(next)) {
            listerMaterielsAlloues();
        }else if("4".equals(next)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pour ajouter un livre tapez 1 :");
            System.out.println("pour ajouter une chaise tapez 2 :");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreAjoute = new Livre();
                Scanner s = new Scanner(System.in);

                System.out.println("Donner le code du Livre a ajoute");
                String codeLivreAjoute = s.nextLine();
                livreAjoute.setName("Livre");
                livreAjoute.setCode(codeLivreAjoute);
                ajouterNouveauMateriel(livreAjoute);

            }else if("2".equals(nx)) {
                Materiel chaiseAjoutee = new Chaise();
                Scanner s = new Scanner(System.in);
                System.out.println("Donner le code de la chaise a ajoutee");
                String codeChaiseAjoutee = s.nextLine();
                chaiseAjoutee.setName("Chaise");
                chaiseAjoutee.setCode(codeChaiseAjoutee);
                ajouterNouveauMateriel(chaiseAjoutee);
            }
        }
        else if("5".equals(next)) {

            Scanner sc = new Scanner(System.in);
            System.out.println("pour modifier un livre tapez 1 :");
            System.out.println("pour modifier une chaise tapez 2 :");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreAModifie = new Livre();

                System.out.println("Donner l'ancien code du Livre a modifier :");
                String codeAncienLivre = sc.nextLine();
                livreAModifie.setCode(codeAncienLivre);

                System.out.println("Donner le nouveau code  du Livre :");
                String codeNouveauLivre = sc.nextLine();
                modifierMateriel(livreAModifie,codeNouveauLivre);
            }else if("2".equals(nx)) {
                Materiel chaiseAModifie = new Chaise();

                System.out.println("Donner l'ancien code de la Chaise a modifier :");
                String codeAncienLivre = sc.nextLine();
                chaiseAModifie.setCode(codeAncienLivre);

                System.out.println("Donner le nouveau code  de la chaise :");
                String codeNouveauLivre = sc.nextLine();
                modifierMateriel(chaiseAModifie,codeNouveauLivre);
            }

        } else if("6".equals(next)) {

            Scanner sc = new Scanner(System.in);
            System.out.println("pour supprimer un livre tapez 1 :");
            System.out.println("pour supprimer une chaise tapez 2 :");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreASupprime = new Livre();
                System.out.println("Donner le code du Livre a supprime :");
                String codeLivreSupprime = sc.nextLine();
                livreASupprime.setCode(codeLivreSupprime);
                supprimerMateriel(livreASupprime);
                System.out.println("le livre "+codeLivreSupprime+ "est supprime");

            }else if("2".equals(nx)) {
                Materiel chaiseASupprime = new Chaise();
                System.out.println("Donner le code de la chaise a supprime");
                String codeChaiseSupprime = sc.nextLine();
                chaiseASupprime.setCode(codeChaiseSupprime);
                supprimerMateriel(chaiseASupprime);
                System.out.println("la chaise "+chaiseASupprime+ "est supprimee");

            }
        }
        else if("7".equals(next)){
            Scanner sc = new Scanner(System.in);
            System.out.println("pour chercher un livre tapez 1 :");
            System.out.println("pour chercher une chaise tapez 2 :");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreRecherche = new Livre();
                System.out.println("Donner le code du Livre que vous cherchez :");
                String codeLivre = sc.nextLine();
                livreRecherche.setCode(codeLivre);
                RechercheMateriel(livreRecherche);
            }else if("2".equals(nx)) {
                Materiel chaiseRecherche = new Chaise();
                System.out.println("Donner le code du Livre que vous cherchez :");
                String codeLivre = sc.nextLine();
                chaiseRecherche.setCode(codeLivre);
                RechercheMateriel(chaiseRecherche);
            }
        }
        else if ("8".equals(next)) {

            Scanner sc = new Scanner(System.in);
            System.out.println("pour allouer un livre tapez 1 ");
            System.out.println("pour allouer une chaise tapez 2 ");
            String nx = sc.nextLine();
            User user = new User();
            user.setUsername(username);
            if("1".equals(nx)) {
                Materiel livre = new Livre();
                livre.setName("Livre");
                System.out.println("donner le code du livre:");
                String code = sc.nextLine();
                livre.setCode(code);
                allouerMateriel(livre,user);
            }else if("2".equals(nx)) {
                Materiel chaise = new Chaise();
                chaise.setName("Chaise");
                System.out.println("donner le code de la chaise :");
                String code = sc.nextLine();
                chaise.setCode(code);
                allouerMateriel(chaise,user);
            }
        }

       else if ("9".equals(next)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pour rendre un livre tapez 1 ");
            System.out.println("pour rendre une chaise tapez 2 ");
            String nx = sc.nextLine();
            if("1".equals(nx)) {
                Materiel livreARendre = new Livre();
                livreARendre.setName("Livre");
                System.out.println("Donner le code du Livre a rendre");
                String codLivre = sc.nextLine();
                livreARendre.setCode(codLivre);
                RendreMateriel(livreARendre,username);
            }else if("2".equals(nx)) {
                Materiel chaiseARendre = new Chaise();
                chaiseARendre.setName("Chaise");
                System.out.println("Donner le code du Livre a rendre :");
                String codChaise = sc.nextLine();
                chaiseARendre.setCode(codChaise);
                RendreMateriel(chaiseARendre,username);
            }
        }
        else {
            System.out.println("choix invalide !!!");
        }

    }
}
