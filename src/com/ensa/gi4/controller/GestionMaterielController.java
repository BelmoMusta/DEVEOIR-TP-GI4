package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AllocationService;
import com.ensa.gi4.service.api.AuthentificationService;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
    @Autowired
    ApplicationPublisher publisher;
    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;
    @Autowired
    private AuthentificationService authentificationService;
    @Autowired
    private AllocationService allocationService;
    private boolean isAuthentified=false;
    private User exist;
    private String role;
    private Long uid;
    public int seConnecter() throws NoSuchAlgorithmException {
        System.out.println("#########################################################################################");
        System.out.println("##               + pour sortir de l'application, entrer (0)                            ##");
        System.out.println("##               + Si vous avez déja un compte, entrer (1)                             ##");
        System.out.println("##               + pour créer un compte, entrer (2)                                    ##");
        System.out.println("#########################################################################################");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        int r=0;
        if ("0".equals(next)) {
            //System.exit(0);
            sortirDeLApplication();
        }else if ("1".equals(next)) {
            //System.exit(0);
            //getAccess();
            r=1;
        }else if ("2".equals(next)) {
            //System.exit(0);
            signUp();
        }else{
            System.out.println("choix non valide");
            seConnecter();
        }
        return  r;
    }
    public void authentification() throws NoSuchAlgorithmException {
        System.out.println("-------------------Log In-------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir votre nom:");
        String name=scanner.next();
        System.out.println("Saisir votre mot de passe:");
        String password=scanner.next();
        exist=authentificationService.login(name,password);

        if(exist!=null && exist.getRole().equals("admin")){
            role=exist.getRole();
            uid=exist.getId();
            System.out.println("Bienvenu "+exist.getName());
            afficherAdminMenu();
            isAuthentified=true;}
        else if(exist!=null && exist.getRole().equals("user")){
            uid=exist.getId();
            role=exist.getRole();
            System.out.println("Bienvenu "+exist.getName());
            afficherUserMenu();
            isAuthentified=true;
        }
        else {
            System.out.println("nom ou mot de passe incorrect!!");
            System.out.println("Merci de saisir des informations corrects ,ou de quitter l'application");
        }
    }
    public void signUp() throws NoSuchAlgorithmException {
        System.out.println("-------------------Sign Up-------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir votre nom:");
        String name=scanner.next();
        System.out.println("Saisir votre mot de passe:");
        String password=scanner.next();
        boolean gotoLogin=authentificationService.signUp(name,password);
        if(gotoLogin){
        getAccess();
        }
        else{
            seConnecter();
        }
    }
    public void afficherUserMenu(){
        System.out.println("#########################################################################################");
        System.out.println("##               + pour sortir de l'application, entrer (0)                            ##");
        System.out.println("##               + pour lister les matériels, entrer (1)                               ##");
        System.out.println("##               + pour chercher un materiel, entrer (2)                               ##");
        System.out.println("##               + pour allouer un materiel, entrer (3)                                ##");
        System.out.println("##               + pour afficher les materiels allouer par vous, entrer (4)            ##");
        System.out.println("##               + pour rendre un materiel alloué par vous, entrer (5)                 ##");
        System.out.println("#########################################################################################");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            //System.exit(0);
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            System.out.println(gestionMaterielService.findAll());
        } else if ("2".equals(next)) {
            Long id;
            System.out.println("Saisir l'id de matériel dont vous cherchez:");
            id=scanner.nextLong();

            try {
                System.out.println(gestionMaterielService.findOne(id));
            }catch (Exception e){
                System.out.println("matériel pas disponible!!!!!!");
            }
        }else if ("3".equals(next)) {
            Long id;
            System.out.println("Saisir l'id du matériel souhaité:");
            id=scanner.nextLong();
            allocationService.allocateMaterial(id,uid);
            //System.out.println("allocation with succes!!");
        }else if ("4".equals(next)) {
            System.out.println("voici les maériels alloués par vous:");
            System.out.println(allocationService.findMaterialAllocated(uid));
        }else if ("5".equals(next)) {
            Long id;
            System.out.println("Saisir l'id du matériel que vous voulez rendre:");
            id=scanner.nextLong();
            allocationService.returnMaterial(id);
            //System.out.println("Le matériel a été rendu avec succé,Merci!!");
        }
    }
    public void afficherAdminMenu() {
        //Scanner scanner  = new Scanner(System.in);
        //publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
        //gestionMaterielService.init();
        System.out.println("#########################################################################################");
        System.out.println("##         + pour sortir de l'application, entrer (0)                                  ##");
        System.out.println("##         + pour lister les matériels, entrer (1)                                     ##");
        System.out.println("##         + pour ajouter un nouveau matériel, entrer (2)                              ##");
        System.out.println("##         + pour supprimer un materiel, entrer (3)                                    ##");
        System.out.println("##         + pour modifier un materiel, entrer (4)                                     ##");
        System.out.println("##         + pour chercher un materiel, entrer (5)                                     ##");
        System.out.println("##         + pour marquer un matériel indisponible, entrer (6)                         ##");
        System.out.println("##         + pour allouer un matériel , entrer (7)                                     ##");
        System.out.println("##         + pour rendre un matériel , entrer (8)                                      ##");
        System.out.println("##         + pour afficher les matériels alloués par vous, entrer (9)                  ##");
        System.out.println("##         + pour afficher les matériels alloués par chaque utilisateur, entrer (10)   ##");
        System.out.println("#########################################################################################");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            //System.exit(0);
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            System.out.println(gestionMaterielService.findAll());
        } else if ("2".equals(next)) {

            String name;
            String code;
            Integer stock;
            System.out.println("Add name:");

            name = scanner.next();
            System.out.println("Add code:");
            code = scanner.next();
            System.out.println("Add Stock");
            stock=scanner.nextInt();
            if(code.equals("LI")){
                Materiel mat = new Livre(name,stock);
                gestionMaterielService.addMaterial(mat);
            }else if(code.equals("CH")){
                Materiel mat = new Chaise(name,stock);
                gestionMaterielService.addMaterial(mat);
            }else{
                Materiel mat = new Livre(name,code,stock) {
                };
                gestionMaterielService.addMaterial(mat);
            }

        } else if ("3".equals(next)) {


            Long id;
            System.out.println("Saisir l'id de matériel que vous voulez supprimer:");
            id = scanner.nextLong();
            gestionMaterielService.deleteMaterial(id);

        } else if ("4".equals(next)) {


            String name;
            Long id;
            Integer stock;
            String code;
            Materiel materiel= new Materiel() {
            };
            System.out.println("Saisir l'id:");
            id = scanner.nextLong();
            System.out.println("Saisir le nom ou 'no' pour garder l'ancienne valeur du nom");
            name = scanner.next();
            System.out.println("Saisir le code ou 'no' pour garder l'ancienne valeur du code");
            code = scanner.next();
            System.out.println("Saisir le stock ou '-1' pour garder l'ancienne valeur du stock");
            stock=scanner.nextInt();
            materiel.setName(name);
            materiel.setId(id);
            materiel.setStock(stock);
            materiel.setCode(code);
            gestionMaterielService.updateMaterial(materiel);

        }
    else if ("5".equals(next)) {

        Long id;
        System.out.println("Saisir l'id de matériel dont vous cherchez:");
        id=scanner.nextLong();
        try{
        System.out.println(gestionMaterielService.findOne(id));
        }catch (Exception e){
            System.out.println("Le matériel que vous cherchés n'existe pas!!!");
        }

    }    else if ("6".equals(next)) {

            Long id;
            System.out.println("Saisir l'id de matériel que vous voulez le mettre non disponible:");
            id=scanner.nextLong();
            gestionMaterielService.makeMaterialUnavailable(id);

        }
        else if ("7".equals(next)) {
            Long id;
            System.out.println("Saisir l'id du matériel souhaité:");
            id=scanner.nextLong();
            allocationService.allocateMaterial(id,uid);
            //System.out.println("allocation with succes!!");
        }
        else if ("8".equals(next)) {
            Long id;
            System.out.println("Saisir l'id du matériel que vous voulez rendre:");
            id=scanner.nextLong();
            allocationService.returnMaterial(id);
            //System.out.println("Le matériel a été rendu avec succé,Merci!!");
        }
        else if ("9".equals(next)) {
            System.out.println("voici les maériels alloués par vous:");
            System.out.println(allocationService.findMaterialAllocated(uid));
        }
        else if ("10".equals(next)) {
            System.out.println("voici les maériels alloués par tous les utilisateurs:");
            allocationService.findMaterialAllocated();
        }else {
            System.out.println("choix invalide");
        }
    }
    public void getAccess() throws NoSuchAlgorithmException {
        if(isAuthentified==false){
            authentification();
        }
        else if(role.equals("admin")){
            afficherAdminMenu();
        }else if(role.equals("user")){
            afficherUserMenu();
        }
    }
    private void sortirDeLApplication() {
        System.exit(0);
    }

}
