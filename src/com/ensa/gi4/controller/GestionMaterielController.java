package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.datatabase.impl.RoleDAOImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;
    @Autowired
    private GestionMaterielService materielServiceBean;
    @Autowired
    private UserDAO user;
    @Autowired
    private RoleDAOImpl role;
    
    @Value("${msg.errorlogin}")
    private String errorlogin;
    @Value("${msg.exit}")
    private String exit;
   

    public void afficherMenu() {
        
          //authentification
        User current = user.Authentification();
        
        //si utilisateur trouvé
        if(current!=null)
        {
           String rol = role.findRole(String.valueOf(current.getIdR())).getName();
            
            //affichage du menu d'administrateur si admin loged in 
        if(rol.equalsIgnoreCase("admin"))
        {
            getMenuAdmin(current.getIdU());
        }
        
        else if(rol.equalsIgnoreCase("Employee"))
        {
            getMenuUser(current.getIdU());
        }
        
        else {
            sortirDeLApplication();
        }
        }
        
        else{
            System.out.println(errorlogin);
            System.out.println("1- pour continuer, entrer 1");
                System.out.println(exit);
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();

        switch (next)
                {
            case "0":  sortirDeLApplication(); break;
            case "1":  break;
            default: System.out.println("choix invalide"); break;
        }
        }
 
       
    }
    
    
    
     public void getMenuAdmin(int idU){
        System.out.println("1- pour Récuperer la liste de tout les matériels, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour chercher un matériel, entrer 3");
        System.out.println("4- pour supprimer un matériel, entrer 4");
        System.out.println("5- pour marquer un matériel indisponible, entrer 5");
        System.out.println("6- pour modifier les informations d'un matériel, entrer 6");
        System.out.println("7- pour allouer un matériel, entrer 7");
        System.out.println("8- pour rendre un matériel, entrer 8");
        System.out.println("9- pour lister vos matériels allouer, entrer 9");
        System.out.println("10- pour lister les matériels allouer par un autre employée, entrer 10");
        System.out.println("0- pour sortir de l'application, entrer 0");
        
        Materiel materiel = null;
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        switch (next)
                {
            
            case "0":  sortirDeLApplication(); break;
            case "1": materielServiceBean.listerMateriel(); break;
            case "2": materielServiceBean.ajouterNouveauMateriel(); publisher.publish(new MyEvent<>(new Livre(), EventType.ADD)); break;
            case "3": materielServiceBean.chercherMateriel(); break;
            case "4": materielServiceBean.supprimerMateriel(); break;
            case "5": materielServiceBean.modifierDisponibilitéMateriel(); break;
            case "6": materielServiceBean.modifierMateriel(); publisher.publish(new MyEvent<>(new Livre(), EventType.UPDATE)); break;
            case "7": materielServiceBean.allouerMateriel(idU);break; 
            case "8": materielServiceBean.rendreMateriel(idU); break;
            case "9": materielServiceBean.listerVosMaterielAllouer(idU);break;
            case "10": materielServiceBean.listerEmployeMaterielAllouer();break;
            default: System.out.println("choix invalide"); break;
        }

        
       
        getMenuAdmin(idU);
    }

    public void getMenuUser(int idU) {
        System.out.println("1- pour Récuperer la liste de tout le matériel, entrer 1");
        System.out.println("2- pour chercher un matériel, entrer 2");
        System.out.println("3- pour allouer un matériel, entrer 3");
        System.out.println("4- pour rendre un matériel, entrer 4");
        System.out.println("5-pour lister vos matériels allouer, entrer 5");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        
        switch (next)
                {
            
            case "0":  sortirDeLApplication(); break;
            case "1": materielServiceBean.listerMateriel(); break;
            case "2": materielServiceBean.chercherMateriel(); break;
            case "3": materielServiceBean.allouerMateriel(idU);break; 
            case "4": materielServiceBean.rendreMateriel(idU); break;
            case "5": materielServiceBean.listerVosMaterielAllouer(idU);break;
            default: System.out.println("choix invalide"); break;
        } 
        getMenuUser(idU);
    }
    
    
    private void sortirDeLApplication() {
        System.exit(0);
    }

}
