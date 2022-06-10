package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.AllocatedItem;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.UserServiceImpl;
import com.ensa.gi4.utils.PasswordCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component("controllerPrincipal")
public class GestionMaterielController {

    @Autowired
    UserServiceImpl userService ;

    @Autowired
    GestionMaterielServiceImpl gestionMaterielService;
    @Autowired
    ApplicationPublisher publisher;

    @Value("${homeMenu.admin}")
    String homeAdmin;



    static Scanner scanner  = new Scanner(System.in);


    public void showLogin(){
        System.out.println("----------Connecter-----------");
        System.out.println("Utilisateur : ");
        String username = scanner.next() ;
        System.out.println("Mot de passe : ");
        String password = scanner.next() ;
        if(userService.login(username, PasswordCryptUtil.encryptPassword(password))){
            User userLogged = userService.getUserByName(username);
            userService.setUsercurrent(userLogged);
            this.showHome();
        }else{
            System.out.println(PasswordCryptUtil.encryptPassword(password));
            System.out.println("Erreur de connection");
            System.out.println("[1] : Réessayer");
            System.out.println("[2] : Sortir");
            System.out.print(">>> ");
            String entry = scanner.next();
            checkEntryAfterError(entry);
        }
    }

    public void showHome() {
        if(this.userService.isAdmin()){
            this.showAdminMenu();
        }else {
            this.showUserMenu();
        }
    }

    public void showAdminMenu(){
        String welcomeMsg = String.format("---Bienvenue %s ---",userService.getUsercurrent().getUsername());
        System.out.println(welcomeMsg);
        System.out.println(homeAdmin);
        System.out.print(">>> ");
        String entry = scanner.next();
        checkAdminEntry(entry);
        returnToMenu();
    }
    public void showUserMenu(){
        String welcomeMsg = String.format("---Bienvenue %s ---",userService.getUsercurrent().getUsername());
        System.out.println(welcomeMsg);
        System.out.println("[1] : Chercher un matériel");
        System.out.println("[2] : Allouer un materiel");
        System.out.println("[3] : Rendre un matériel");
        System.out.println("[4] : Afficher la liste des matériels alloués par lui même");
        System.out.println("[5] : Afficher la liste de tous les matériels");
        System.out.println("[0] : Sortir de l'application");
        System.out.print(">>> ");
        String entry = scanner.next();
        checkUserEntry(entry);
        returnToMenu();
    }

    public void checkAdminEntry(String entry){
        switch(entry) {
            case "0" :
                sortirDeLApplication();
            case "1" :
                findOneItem();
                break;
            case "2" :
                addNewItem();
                break;
            case "3" :
                deleteOneItem();
                break;
            case "4" :
                editOneItem();
                break;
            case "5" :
                editAvailable();
                break;
            case "6" :
                allocateItem();
                break;
            case "7" :
                returnItem();
                break ;
            case "8" :
                userItemAllocated();
                break;
            case "9" :
                allItemAllocated();
                break;
            case "10" :
                showAllItems();
                break;
            default :
                System.out.println("Choix invalide ! Veuiller réessayer");
                showAdminMenu();
        }
    }

    public void checkUserEntry(String entry){
        switch(entry){
            case "0" :
                sortirDeLApplication();
            case "1" :
                findOneItem();
                break;
            case "2" :
                allocateItem();
                break;
            case "3" :
                returnItem();
                break;
            case "4" :
                userItemAllocated();
                break;
            case "5" :
                showAllItems();
                break;
            default :
                System.out.println("Choix invalide ! Veuiller réssayer");
                showUserMenu();
        }
    }

    public void checkEntryAfterError(String entry){
        switch(entry){
            case "1" : showLogin();
            case "2" : sortirDeLApplication();
            default: sortirDeLApplication();
        }
    }

    public void returnToMenu(){
        System.out.println("Pour retourner au menu principal appuyez sur 1");
        if("1".equals(scanner.next())) showHome();
        else {
            System.out.println("Pour retourner au menu principal appuyez sur 1");
            returnToMenu();
        }
    }

    public void findOneItem(){
        System.out.println("--------Chercher un matériel-------");
        System.out.println("Id du matériel : ");
        int id = Integer.parseInt(scanner.next()) ;
        Materiel m = this.gestionMaterielService.findOneById(id);
        System.out.println("---------------");
        System.out.println("Id : "+m.getId());
        System.out.println("Nom : "+m.getName());
        System.out.println("Type : "+m.getType());
        System.out.println("Alloué : "+m.getAllocated());
        System.out.println("---------------");
    }

    public void showAllItems(){
        List<Materiel> list = this.gestionMaterielService.showAll();
        System.out.println("--------Liste des matériaux--------");
        for(Materiel m : list){

            System.out.println("---------------");
            System.out.println("Id : "+m.getId());
            System.out.println("Nom : "+m.getName());
            System.out.println("Type : "+m.getType());
            System.out.println("Stock : "+m.getStock());
            System.out.println("Alloué : "+m.getAllocated());
            if(m.getAvailable()) System.out.println("Disponible : yes");
            else System.out.println("Disponible : no");
            System.out.println("---------------");
        }
    }
    public void addNewItem(){
        Materiel materiel = new Materiel();
        System.out.println("--------Ajouter un matériel-------");
        System.out.println("Nom du matériel : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Type du matériel : ");
        String type = scanner.next();
        System.out.println("stock : ");
        int stock = Integer.parseInt(scanner.next());
        materiel.setName(name);
        materiel.setType(type);
        materiel.setStock(stock);
        this.gestionMaterielService.addNew(materiel);
        System.out.println(materiel.getName()+" ajouté avec succès !!");
    }

    public void editOneItem(){
        System.out.println("--------Editer un matériel-------");
        System.out.println("Id du matériel à modifier : ");
        int id = Integer.parseInt(scanner.next()) ;
        Materiel materiel = this.gestionMaterielService.findOneById(id);
        if(materiel==null){
            System.out.println("Matériel non trouvé !! Id introuvable");
            return ;
        }
        System.out.println("Nouveau nom  du matériel : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Nouveau stock  du matériel : ");
        int stock = Integer.parseInt(scanner.next()) ;
        materiel.setName(name);
        materiel.setStock(stock);
        this.gestionMaterielService.editMateriel(materiel);
        System.out.println(materiel.getName()+" modifié avec succès !!");
    }

    public void deleteOneItem(){
        System.out.println("--------Supprimer un matériel-------");
        System.out.println("Id du matériel à supprimer : ");
        int id = Integer.parseInt(scanner.next()) ;
        Materiel materiel = this.gestionMaterielService.findOneById(id);
        if(materiel==null){
            System.out.println("Matériel non trouvé !! Id introuvable");
            return ;
        }
        if(this.gestionMaterielService.deleteMateriel(materiel)){
            System.out.println(materiel.getName()+" supprimé avec succès !!");
        }else System.out.println("Ce matériel ne peut pas etre supprimer car il est deja alloué");
    }

    public void allocateItem(){
        System.out.println("--------Allouer  un matériel-------");
        System.out.println("Id du matériel à allouer : ");
        int id_materiel = Integer.parseInt(scanner.next()) ;
        int id_user = this.userService.getUsercurrent().getId();
        if(this.gestionMaterielService.toAllocate(id_materiel,id_user)){
            System.out.println("Matériel Alloué avec succès");
        }else System.out.println("Le stock est épuisé ou ce produit est indisponible !!");

    }
    public void returnItem(){
        System.out.println("--------Retourner  un matériel-------");
        System.out.println("Id du matériel à retourner : ");
        int id_materiel = Integer.parseInt(scanner.next()) ;
        int id_user = this.userService.getUsercurrent().getId();
        if(this.gestionMaterielService.toReturn(id_materiel,id_user)){
            System.out.println("Matériel retourné avec succès");
        }else System.out.println("Vous n'avez pas alloué ce produit");
    }

    public void editAvailable(){
        System.out.println("--------disponibilité d un matériel-------");
        System.out.println("Id du matériel : ");
        int id = Integer.parseInt(scanner.next()) ;
        if(this.gestionMaterielService.isAvailable(id)) {
            this.gestionMaterielService.editAvailable(id,"no");
            System.out.println("Ce matériel est indisponible");
        }
        else {
            this.gestionMaterielService.editAvailable(id,"yes");
            System.out.println("Ce matériel est disponible maintenant");
        }
    }

    public void allItemAllocated(){
        List<AllocatedItem> list = this.gestionMaterielService.showAllAllocatedItems();
        System.out.println("--------Materiaux alloués-------");
        if(list==null) {
            System.out.println("Aucun Matériel alloué !!");
            return ;
        }
        for(AllocatedItem item : list){
            User user = this.userService.getUserById(item.getId_user());
            Materiel materiel = this.gestionMaterielService.findOneById(item.getId_materiel());
            System.out.println("--------------------");
            System.out.println(materiel.toString());
            System.out.println("alloué par l utilisateur :"+user.getUsername());
        }
    }
    public void userItemAllocated(){
        User user = this.userService.getUsercurrent();
        List<AllocatedItem> list = this.gestionMaterielService.showUserAllocatedItems(user.getId());
        System.out.println("--------Materiaux alloués par "+user.getUsername());
        if(list==null) {
            System.out.println("Aucun Matériel alloué !!");
            return ;
        }
        for(AllocatedItem item : list){
            Materiel materiel = this.gestionMaterielService.findOneById(item.getId_materiel());
            System.out.println("--------------------");
            System.out.println(materiel.toString());
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
