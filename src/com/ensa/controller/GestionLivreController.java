package com.ensa.controller;

import com.ensa.dao.AllocationDao;
import com.ensa.modele.Allocation;
import com.ensa.modele.Chaise;
import com.ensa.modele.Livre;
import com.ensa.modele.User;
import com.ensa.service.api.GestionLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Scanner;
@Component("livrecont")
public class GestionLivreController implements MaterielController {

    private GestionLivreService gestionLivreService;
    @Autowired
    private AllocationDao allo;
@Override
    public void listerMateriel() {
        gestionLivreService.listerMateriel();
    }
    @Override
    public void afficherMenu(User user) {
        System.out.println("Retour, entrer 1");
        System.out.println("Lister tous les livres, entrer 2");
        System.out.println("pour allouer , entrer 3");
        System.out.println("pour rendre , entrer 4");
        System.out.println("pour chercher un livre ,entrer 5");
        if(user.getIsadmin()){
            System.out.println("pour ajouter un livre , entrer 6");
            System.out.println("pour supprimer un livre , entrer 7");
            System.out.println("pour marquer indisponible , entrer 8 ");
            System.out.println("pour modifier , entrer 9 ");
        }

        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {


        }else if ("2".equals(next)) {
            listerMateriel();
        } else if ("6".equals(next) && user.getIsadmin()) {
            String name; float prix; String auteur;String code;
            System.out.println("Entrez le nom");
            Scanner scan = new Scanner(System.in);
            name= scan.next();

            System.out.println("Entrez le prix");
            prix= scan.nextFloat();
            System.out.println("Entrez le code");
            code=scan.next();
            System.out.println("Entrez auteur");
            auteur=scan.next();
            Livre livre=new Livre();
            livre.setPrix(prix);livre.setName(name);livre.setAuteur(auteur);livre.setCode(code);
            gestionLivreService.ajouterNouveauMateriel(livre);





        }else if ("3".equals(next)) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez code ");
            String code=scan.next();
            if(!gestionLivreService.isordered(code)){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Allocation allo =new Allocation();
                allo.setProductid(code);allo.setUserid(user.getUsername());allo.setAllocationdate(date);
                gestionLivreService.allouerMateriel(allo);
                gestionLivreService.updateorder(code,"true");

            }else System.out.println("Deja allouer! ");
        }
        else if ("4".equals(next)) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez code ");
            String code=scan.next();
            gestionLivreService.updateorderforuser(code,user);

        }
        else if ("5".equals(next) ) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez code ");
            String code=scan.next();
            if(gestionLivreService.disponibilite(code)){System.out.println("Disponible ");}
            else System.out.println("Indisponible ");

        }
        else if ("7".equals(next) && user.getIsadmin()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez code ");
            String code=scan.next();
            gestionLivreService.supprimerMateriel(code);

        }
        else if ("8".equals(next)&& user.getIsadmin()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez code ");
            String code=scan.next();
            gestionLivreService.updateorder(code,"false");

        }
        else if ("9".equals(next)&& user.getIsadmin()) {
            String name; float prix; String auteur;String code;String newcode;

            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez le code livre a modifier");
            code=scan.next();
            System.out.println("Entrez le nom");
            name= scan.next();
            System.out.println("Entrez le nouveau code");
            newcode= scan.next();

            System.out.println("Entrez le prix");
            prix= scan.nextFloat();

            System.out.println("Entrez auteur");
            auteur=scan.next();
            Livre livre=new Livre();
            livre.setPrix(prix);livre.setName(name);livre.setAuteur(auteur);livre.setCode(newcode);
            gestionLivreService.modifierMateriel(livre,code);
        }





        //   gestionChaiseService.ajouterNouveauMateriel(nombre);
    }
    @Override
    public void sortirDeLApplication() {
        System.exit(0);
    }

    @Autowired
    @Qualifier("livre")
    public void setGestionLivreService(GestionLivreService gestionLivreService) {
        // injection par accesseur
        this.gestionLivreService = gestionLivreService;
    }
}
