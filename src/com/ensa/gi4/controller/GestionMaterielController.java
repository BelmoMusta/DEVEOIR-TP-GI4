package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.AllocationService;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {


    private GestionMaterielService gestionLivreService;

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AllocationService allocationService;
    Utilisateur utilisateur;

    Materiel mat;
    Boolean temp;
    Scanner scanner = new Scanner(System.in);
    public void listerMateriel() {
        gestionMaterielService.listerMateriel();
    }

    public void ajouterMateriel(String materielName, String materielType, String materielCode){ gestionMaterielService.ajouterNouveauMateriel(materielName, materielType, materielCode);}

    public void supprimerMateriel(String materielName, String materielCode){ gestionMaterielService.supprimerMateriel(materielName, materielCode);}

    public Materiel chercherMateriel(String s){ return gestionMaterielService.chercherMateriel(s);}

    public void modifierMateriel(String materielCode,String materielName,String materielType,String materielAvailable){ gestionMaterielService.modifierMateriel(materielCode, materielName, materielType, materielAvailable);}
    public void madifierEtat(String materielCode, String materielEtat){ gestionMaterielService.modifierAvailable(materielCode, materielEtat);}

    public Utilisateur logginUser(String username, String password){return utilisateurService.chercherUtilisateur(username, password);}

    public void allouerMat(Utilisateur utilisateur, Materiel materiel, String duree){ allocationService.allouerMateriel(utilisateur, materiel, duree); }
    public void rendreMateriel(Utilisateur utilisateur, Materiel materiel){ allocationService.rendreMateriel(utilisateur, materiel); }

    public void listerMatsAlloue(int idUser) { allocationService.listerMaterielAllou(idUser);

    }
    public void listerTousAllocations() { allocationService.listerTouTAllocation(); }

    public GestionMaterielController() {
    }

    public GestionMaterielController(GestionMaterielService livreService) {
        this.gestionLivreService = livreService;
    }


    public void logging(){
        System.out.println("1- pour s'authentifier, entrer 1");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if("1".equals(next)) {

            System.out.print("Entrer le nom d'utilisateur: ");
            String username = scanner.next();
            System.out.print("Entrer le mot de passe: ");
            String password = scanner.next();

            utilisateur = logginUser(username, password);

            if(utilisateur != null){
                temp = true;
                if(utilisateur.getRole().equals("administrateur")){
                    while(temp){
                        afficherMenu();
                    }

                } else if(utilisateur.getRole().equals("employee")){
                    while(temp){
                        afficherMenuEmp();
                    }
                }

            } else{
                temp = false;
            }

        }
    }

    public void afficherMenuEmp(){
        System.out.println("0- pour sortir de l'application, entrer 0");
        System.out.println("1- pour chercher un  matériel, entrer 1");
        System.out.println("2- pour allouer un  matériel, entrer 2");
        System.out.println("3- pour rendre un  matériel, entrer 3");
        System.out.println("4- pour afficher la liste des materiel alloues par vous");
        System.out.println("5- pour lister le matériel, entrer 5");
        System.out.println("6- pour se deconneter, entrer 6");

        String next2 = scanner.next();
        if("0".equals(next2)){

            sortirDeLApplication();
        }
        else if ("1".equals(next2)){

            System.out.print("Entrer le code du materiel à chercher : ");
            String materielCode = scanner.next();
            System.out.println(chercherMateriel(materielCode));

        } else if ("2".equals(next2)) {

            System.out.print("Entrer le code du materiel que vous voulez allouer : ");
            String materielCode = scanner.next();
            mat = chercherMateriel(materielCode);
            if (mat != null) {
                System.out.print("Entrer la duree d'allocation : ");
                String duree = scanner.next();

                allouerMat(utilisateur, mat, duree);

            }

        }else if ("3".equals(next2)){
            System.out.print("Entrer le code du materiel que vous voulez rendre : ");
            String materielCode = scanner.next();
            Materiel mat = chercherMateriel(materielCode);
            if(mat != null){

                rendreMateriel(utilisateur, mat);

            }
        } else if ("4".equals(next2)){
            listerMatsAlloue(utilisateur.getIdUser());
        } else if ("5".equals(next2)) {
            listerMateriel();
        }  else if ("6".equals(next2)){

            System.out.println("deconnexion effectuée");

            logging();

        } else {

            System.out.println("choix invalide");

        }
    }
    public void afficherMenu() {
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour supprimer un  matériel, entrer 3");
        System.out.println("4- pour chercher un  matériel, entrer 4");
        System.out.println("5- pour modifier un  matériel, entrer 5");
        System.out.println("6- pour modifier la disponibilité d'un  matériel, entrer 6");
        System.out.println("7- pour allouer un  matériel, entrer 7");
        System.out.println("8- pour rendre un  matériel, entrer 8");
        System.out.println("9- pour afficher la liste des materiel alloues par vous");
        System.out.println("10- pour afficher la liste des materiel alloues par chaque utilisateur");
        System.out.println("11- pour se deconnecter");
        System.out.println("0- pour sortir de l'application, entrer 0");

        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();
        } else if ("2".equals(next)) {

            System.out.print("Entrer le nom du nouveau materiel : ");
            String materielName = scanner.next();
            System.out.print("Entrer le type du nouveau materiel (livre, chaise): ");
            String materielType = scanner.next();
            System.out.print("Entrer le code du nouveau materiel (livre, chaise): ");
            String materielCode = scanner.next();

            ajouterMateriel(materielName, materielType, materielCode);



        } else if ("3".equals(next)){

            System.out.print("Entrer le nom du materiel à supprimer: ");
            String materielName = scanner.next();
            System.out.print("Entrer le code du materiel à supprimer : ");
            String materielCode = scanner.next();

            supprimerMateriel(materielName, materielCode);

        } else if ("4".equals(next)){

            System.out.print("Entrer le code du materiel à chercher : ");
            String materielCode = scanner.next();
            System.out.println(chercherMateriel(materielCode));

        } else if("5".equals(next)){
            System.out.print("Entrer le code du materiel à modifier : ");
            String materielCode = scanner.next();
            mat = chercherMateriel(materielCode);
            if(mat != null){

                System.out.print("Entrer le nouveau nom du materiel: ");
                String materielName = scanner.next();
                System.out.print("Entrer le nouveau type du materiel: ");
                String materielType = scanner.next();
                System.out.print("est il disponible (oui ou non): ");
                String materielAvailable = scanner.next();
                modifierMateriel(materielCode, materielName, materielType, materielAvailable);
            }



        } else if ("6".equals(next)){

            System.out.print("Entrer le code du materiel à modifier l'etat : ");
            String materielCode = scanner.next();
            System.out.println("Ce materiel est il disponible (oui ou non):");
            String materielEtat = scanner.next();

            madifierEtat(materielCode, materielEtat);
        }else if ("7".equals(next)){
            System.out.print("Entrer le code du materiel que vous voulez allouer : ");
            String materielCode = scanner.next();
            mat = chercherMateriel(materielCode);
            if (mat != null) {
                System.out.print("Entrer la duree d'allocation : ");
                String duree = scanner.next();

                allouerMat(utilisateur, mat, duree);

            }


        }else if ("8".equals(next)){
            System.out.print("Entrer le code du materiel que vous voulez rendre : ");
            String materielCode = scanner.next();

            Materiel mat = chercherMateriel(materielCode);
            if(mat != null){
                rendreMateriel(utilisateur, mat);
            }

        }else if ("9".equals(next)){

            listerMatsAlloue(utilisateur.getIdUser());

        } else if ("10".equals(next)){

            listerTousAllocations();

        }else if ("11".equals(next)){

            System.out.println("deconnexion effectuée");

            logging();

        }else {

            System.out.println("choix invalide");
        }
     /*   Scanner scanner  = new Scanner(System.in);
        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD)); */
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
