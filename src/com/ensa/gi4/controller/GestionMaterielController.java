package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.GestionUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
	
    @Autowired
    private GestionMaterielServiceImpl gestionMaterielServiceImpl;

    @Autowired
    private GestionUserServiceImpl gestionUserService;

    @Autowired
    ApplicationPublisher publisher;
    
    private User user;
    private Materiel materiel;
   /* public void listerMateriel() {
    	
    	gestionMaterielServiceImpl.listerMateriel();
    }*/

    public void afficherMenu() {
    	  System.out.println("1- Pour vous connecter , entrer 1");
          System.out.println("2-  Pour vous inscrire, entrer 2");
          Scanner scanner = new Scanner(System.in);
          String next = scanner.next();
          if ("1".equals(next)) {
              Connecter();
   
          }else if ("2".equals(next)) {
              inscrire(); 
              Connecter();
              
          }
          else {
              System.out.println("choix invalide");
          }
       
    }
    public void ChoixMateriel(Materiel materiel) {
  	  System.out.println("1-Pour ajouter un livre entrez 1 ");
        System.out.println("2-Ppour ajouter une chaise entrez 2");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        if(type == 1 ) {
             Materiel livre = new Livre();
             scanner = new Scanner(System.in);
             System.out.print("Nom : ");
             String nom = scanner.nextLine();
             System.out.print("Code : ");
             String code = scanner.nextLine();
             livre.setName(nom);
             livre.setCode(code);
             livre.setType("Livre");
             livre.setFree(true);
             livre.setAllocate(false);
             gestionMaterielServiceImpl.ajouterNouveauMateriel(livre);
         
        			}
        else if(type == 2){
        	Materiel chaise = new Chaise();
             scanner = new Scanner(System.in);
             System.out.print("Nom : ");
             String nom = scanner.nextLine();
             System.out.print("Code : ");
             String code = scanner.nextLine();
             chaise.setName(nom);
             chaise.setCode(code);
             chaise.setType("Chaise");
             chaise.setFree(true);
             chaise.setAllocate(false);
     
             gestionMaterielServiceImpl.ajouterNouveauMateriel(chaise);
        }
    }
    
  
 
    
    public void afficherMenuAdmin(){
   
    	System.out.println("1- Lister les materiaux ");
        System.out.println("2- Ajouter un nouveau matériel ");
        System.out.println("3- Supprimer un matériel");
        System.out.println("4- Modifier un matériel");
        System.out.println("5- Chercher un matériel");
        System.out.println("6- Allouer un matériel");
        System.out.println("7- Rendre un matériel");
        System.out.println("8- Lister matériaux empreinté par un utilisateur");
        System.out.println("9- Lister matériaux alloués ");
        System.out.println("10- Se deconnecter "); 
        System.out.println("0- pour sortir de l'application ");
        

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) { 
        	gestionMaterielServiceImpl.listerMateriel();
        } else if ("2".equals(next)) {
        	ChoixMateriel(materiel);
    
        }
        else if ("3".equals(next)) {
        	 gestionMaterielServiceImpl.listerMateriel();
        	System.out.println("Entrez le numero du matériel à supprimer : ");
            Scanner scanner1 = new Scanner(System.in);
            int numMateriel = scanner1.nextInt();
        	gestionMaterielServiceImpl.supprimerMateriel(numMateriel);
        }
        else if("4".equals(next))
        {
        	 gestionMaterielServiceImpl.listerMateriel();
        	System.out.println("Entrez le numero du matériel à modifier : ");
            Scanner scanner1 = new Scanner(System.in);
            int numMateriel = scanner1.nextInt();
        	gestionMaterielServiceImpl.modifierMateriel(numMateriel);
        }
        else if("5".equals(next))
        	
        {
        	 gestionMaterielServiceImpl.listerMateriel();
        	System.out.println("Entrez le numero du matériel à rechercher : ");
            Scanner scanner1 = new Scanner(System.in);
            int numMateriel =scanner1.nextInt();
        	gestionMaterielServiceImpl.chercherMateriel(numMateriel);
        }
        else if("6".equals(next))
        {
        	  gestionMaterielServiceImpl.listerMateriel();
              System.out.println("Entrez le numero du matériel à allouer : ");
              int id = scanner.nextInt();
              if(gestionMaterielServiceImpl.isFree(id)) {
                 gestionMaterielServiceImpl.allouerMateriel(id,user.getIdUser());
               
              }else System.out.println("Materiel indisponible ");
    
        }
        else if("7".equals(next))
        {
        	 gestionMaterielServiceImpl.listerMateriel();
        	 System.out.println("Entrer le id du matériel à rendre : ");
             Scanner scanner1 = new Scanner(System.in);
             int id = scanner1.nextInt();
        	gestionMaterielServiceImpl.rendreMateriel(id);
        }
        else if("8".equals(next))
        {
       	gestionMaterielServiceImpl.listerMaterielAllouerChaqueUser();
        }
        else if("9".equals(next))
        {
        	gestionMaterielServiceImpl.listerMaterielAlloues();
        }
        else if("10".equals(next))
        {
            System.out.println("Deconnexion");
            afficherMenu();
        }
        else {
            System.out.println("choix invalide");
        }

        afficherMenuAdmin();
    }
        
    public void afficherMenuUser() {
    	System.out.println("1- Lister les materiaux ");
        System.out.println("2- Chercher un matériel");
        System.out.println("3- Allouer un matériel");
        System.out.println("4- Rendre un matériel");
        System.out.println("5- Lister vos matériaux empreintés ");
        System.out.println("6- Se deconnecter "); 
        System.out.println("0- pour sortir de l'application ");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
        	gestionMaterielServiceImpl.listerMateriel();
        }
        else if ("2".equals(next)) {
        	{
            	System.out.println("Entrez le numero du matériel à rechercher : ");
                Scanner scanner1 = new Scanner(System.in);
                int numMateriel =scanner1.nextInt();
            	gestionMaterielServiceImpl.chercherMateriel(numMateriel);
            }
        }
        else if("3".equals(next))
        {
        	gestionMaterielServiceImpl.listerMateriel();
       	 System.out.println("Entrer le id du matériel à rendre : ");
            Scanner scanner1 = new Scanner(System.in);
            int id = scanner1.nextInt();
       	gestionMaterielServiceImpl.rendreMateriel(id);
        }
        else if("4".equals(next))
        {
        	//gestionMaterielServiceImpl.rendreMateriel();
        }
        else if("5".equals(next))
        {
       	gestionMaterielServiceImpl.listerMaterielAllouerChaqueUser();
        }
        else if("6".equals(next))
        {
            System.out.println("Se Deconnecter");
            afficherMenu();
        }
        else {
            System.out.println("choix invalide");
        }

        afficherMenuUser();
    }


    public void inscrire(){
        System.out.println("Entrez username :");
        Scanner scanner1 = new Scanner(System.in);
        String usermane = scanner1.nextLine();
        System.out.println("Entrez mot de passe  :");
        Scanner scanner2 = new Scanner(System.in);
        String password = scanner2.nextLine();
        String passwordHashed = doHashing(password);
        gestionUserService.register(usermane,passwordHashed);
    }
    public void Connecter(){
    	System.out.println("///// CONNEXION //////");
    	System.out.println("Entrez username :");
        Scanner scanner1 = new Scanner(System.in);
        String username = scanner1.nextLine();
        System.out.println("Entrez mot de passe  :");
        Scanner scanner2 = new Scanner(System.in);
        String password = scanner2.nextLine();
       // String passwordHashed = doHashing(password);
        
        user =  gestionUserService.login(username, password);
        if(user.getRole().equals("admin")) {
           
           afficherMenuAdmin();
           } 
        else if (user.getRole().equals("user")){
        	   afficherMenuUser();
      }
        else {
        	System.out.println("Username ou votre password invalid :");
            sortirDeLApplication();
        }
    }
    public String doHashing(String password)
    {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : resultByteArray)
            {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
