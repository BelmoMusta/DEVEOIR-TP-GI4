package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
    @Autowired
    GestionUserService gestionUserService;
@Autowired
    GestionMaterielService gestionMaterielService;

//    @Autowired
//
//    ApplicationPublisher publisher;


//    public void afficherUser(){
//        System.out.println("la liste des users diponible est "+gestionUserService.listerUser());
//    }

    public void loginUser(){
        try{

        System.out.println("Pour se connecter veuillez entrer vos données");
        System.out.println("Entrez votre nom");
        Scanner scanner  = new Scanner(System.in);
        String name=scanner.next();
        System.out.println("Enter votre mot de pass");
        String password=scanner.next();
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        String role=gestionUserService.listerRole(user).get(0).getRole();

        System.out.println("role"+" "+role);


        if(role.equals("admin")){

            adminMenu();
        }
        else if(role.equals("employee")){


            employeeMenu();
        }
        else {
            System.out.println("veuillez saisir vos données à nouveau ");
        }
    }
        catch (IndexOutOfBoundsException e){
            System.out.println("Vos données sont incorrectes!!!!!!!!!!!");
            System.out.println("  ");
            System.out.println("  ");

        }




    }
    public void adminMenu(){

        System.out.println("admin here");
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour supprimer un  matériel, entrer 3");
        System.out.println("4- pour modifier un  matériel, entrer 4");
        System.out.println("5- pour chercher un  matériel, entrer 5");
        System.out.println("6-pour rendre un materiel  indisponible, entrer 6");

        System.out.println("7- pour allouer un materiel, entrer 7");
        System.out.println("8- pour rendre un materiel, entrer 8");
        System.out.println("9- pour lister tous  les materiels alloues , entrer 9");
        System.out.println("10- pour lister tous  les materiels alloues par chaque utilisateur , entrer 10");

        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("0".equals(next)) {
            sortirDeLApplication();
        }

        else if ("1".equals(next)) {

        List<Materiel> materiels=   gestionMaterielService.listerMateriel();
            System.out.println("Liste du materiel");
        for(Materiel materiel:materiels){

            System.out.println("[nom,code] = "+"["+materiel.getName()+","+materiel.getCode()+"]");
        }
        } else if ("2".equals(next)) {
            // ajouter nouveau matériel
            Materiel m=new Materiel() {
            };
            System.out.println("si vous voulez entrer livre taper 1 sinon taper 2");

            Scanner scanner1 = new Scanner(System.in);
            int  next1 = Integer.parseInt(scanner.next());
            switch (next1){
                case 1:
                    m  =new Livre();
                    break;
                case 2:
                    m=new Chaise();
                    break;
                default:
                    System.out.println("choix invalid ");

            }


            System.out.println("entrer le nom de votre materiel");
            Scanner s1 = new Scanner(System.in);
            String n = s1.next();
            System.out.println("entrer le code de votre materiel");
            Scanner s2 = new Scanner(System.in);
            String c =s2.next();
            m.setName(n);
            m.setCode(c);



            gestionMaterielService.ajouterNouveauMateriel(m);
        }
        else if("3".equals(next)){
            System.out.println("entrer l id  du materiel que vous souhaitez supprimer  ");
            Scanner s3 = new Scanner(System.in);
            int id = scanner.nextInt();


        gestionMaterielService.supprimerMateriel(id);

        }
        else if("4".equals(next)){
            //id
            System.out.println("entrer l'id du materiel que vous souhaitez modifier");
            Scanner s = new Scanner(System.in);
            int id = s.nextInt();

            System.out.println("entrer le nouveau nom");
            Scanner s5 = new Scanner(System.in);
            String n5 = scanner.next();
            System.out.println("entrer le nouveau code");
            Scanner s6 = new Scanner(System.in);
            String n6 = scanner.next();



                //nom&code
                gestionMaterielService.modifierNomMateriel( n5,id);
                gestionMaterielService.modifierCodeMateriel( n6,id);

            System.out.println("La modification du materiel dont l 'id est "+id+" a ete effectue avec succes");



        }

        else if ("5".equals(next)) {
            System.out.println("Entrez l 'id du materiel cherche ");
            Scanner s = new Scanner(System.in);
          Long id = s.nextLong();


            System.out.println("votre materiel cherche est "+" "+"[Nom,Code]=["+gestionMaterielService.chercherMateriel(id).getName()+","+gestionMaterielService.chercherMateriel(id).getCode()+"]");
        }
        else if("6".equals(next)){
            System.out.println("Rendre un materiel  indisponible ,veuillez entrer son id");
            Scanner s = new Scanner(System.in);
            Long id = s.nextLong();
            gestionMaterielService.rendreMaterielIndisponible(id);
            System.out.println("La materiel dont le nom "+" "+gestionMaterielService.chercherMateriel(id)+" et son id=[" +id+"] "+"est maintenant indiponible");



        }
        else if ("7".equals(next)) {
            System.out.println("Si vous souhaitez allouer un materiel veuillez re-saisir votre nom ");
            Scanner s = new Scanner(System.in);
            String name = s.next();
            System.out.println("Entrez l'id du materiel à allouer");
            Scanner s1 = new Scanner(System.in);
            Long id = s1.nextLong();

          if(gestionMaterielService.chercherMateriel(id).isDisponible()==false){

            gestionMaterielService.chercherMateriel(id).setNonAlloue(false);
            gestionMaterielService.chercherMateriel(id).setAlloue(true);
               gestionMaterielService.allouerMateriel(name,true,false,id);
              System.out.println("Le livre dont l 'id est ="+" "+id+" a ete allouer avec succes par l'utilisateur "+" "+name);
          }
          else {

              System.out.println("Ce materiel dont l'id est "+ id + " n'est pas disponible");
          }


        }
        else if ("8".equals(next)) {
            System.out.println("Entrez l'id du materiel à rendre");
            Scanner s1 = new Scanner(System.in);
            Long id = s1.nextLong();
            if(gestionMaterielService.chercherMateriel(id).isNonAlloue()==true){
                System.out.println("Ce livre dont l'id est "+" "+id+"n'est pas alloue ");
            }
            else if (gestionMaterielService.chercherMateriel(id).isAlloue()==true){
                gestionMaterielService.rendreMateriel(false,true,id);
                System.out.println("vous avez rendu le livre ["+gestionMaterielService.chercherMateriel(id).getName()+","+gestionMaterielService.chercherMateriel(id).getCode()+"] avec succes!");
            }


        }
        else if ("9".equals(next)) {
           List<Materiel> materiels=gestionMaterielService.listerMateriel();
           int i=0;
            for (Materiel materiel:materiels
                 ) {
                if (materiel.isAlloue()==true){
                    System.out.println("Les livres alloues sont "+" "+"["+materiel.getName()+","+materiel.getCode()+"]");

                }
               else {
                   i=i+1;


               }


            }
            if(i!=0){
                System.out.println("aucun materiel n'est alloue");
            }

        }
        else if ("10".equals(next)) {
            List<Materiel> materiels=gestionMaterielService.listerMateriel();
            int i=0;
            for (Materiel materiel:materiels
            ) {
                if (materiel.getUser().equals("admin")){
                if (materiel.isAlloue()==true){
                    System.out.println("Les livres alloues par l' utilisateur"+" "+materiel.getUser()+" "+" sont "+" "+"["+materiel.getName()+","+materiel.getCode()+"]");

                }

                }
                else if(materiel.getUser().equals("employee")){

                    if (materiel.isAlloue()==true){
                        System.out.println("Les livres alloues par l'utilisateur"+" "+materiel.getUser()+" "+"sont "+" "+"["+materiel.getName()+","+materiel.getCode()+"]");

                    }

                }
                else {

                  i=i+1;
                }


            }
            if (i!=0){
                System.out.println("aucun livre n'est alloue ");
            }

        }
        else {
            System.out.println("choix invalide");
        }
    }



    public void employeeMenu(){
        System.out.println("employee here ");
        System.out.println("taper 0 pour sortir de l'application");
        System.out.println("taper 1 pour chercher du materiel");

        System.out.println("taper 2 pour allouer du materiel");
        System.out.println("taper 3 pour afficher liste des materiels alloués ");
        System.out.println("taper 4 pour lister du materiel");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        }
        else if("1".equals(next)){


            System.out.println("Entrez l 'id du materiel cherche ");
            Scanner s = new Scanner(System.in);
            Long id = s.nextLong();


            System.out.println("votre materiel cherche est "+" "+"[Nom,Code]=["+gestionMaterielService.chercherMateriel(id).getName()+","+gestionMaterielService.chercherMateriel(id).getCode()+"]");


        }
        else if("2".equals(next)){
            System.out.println("Si vous souhaitez allouer un materiel veuillez resaisir votre nom ");
            Scanner s = new Scanner(System.in);
            String name = s.next();
            System.out.println("Entrez l'id du materiel à allouer");
            Scanner s1 = new Scanner(System.in);
            Long id = s1.nextLong();

            if(gestionMaterielService.chercherMateriel(id).isDisponible()==false){

                gestionMaterielService.chercherMateriel(id).setNonAlloue(false);
                gestionMaterielService.chercherMateriel(id).setAlloue(true);
                gestionMaterielService.allouerMateriel(name,true,false,id);
                System.out.println("Le livre dont l 'id est ="+" "+id+"a ete allouer avec succes par l'utilisateur "+" "+name);
            }
            else {

                System.out.println("Ce materiel dont l'id est "+ id + " n'est pas disponible");
            }

        }
        else if("3".equals(next)){

            List<Materiel> materiels=gestionMaterielService.listerMateriel();
            for (Materiel materiel:materiels
            ) {
                if (materiel.isAlloue()==true){
                    System.out.println("Les livres alloues sont "+" "+"["+materiel.getName()+","+materiel.getCode()+"]");

                }
                else {

                    System.out.println("Tous les materiels ne sont pas alloues !");
                }


            }
        }
        else if("4".equals(next)){
            System.out.println("Liste du materiel");
            System.out.println(gestionMaterielService.listerMateriel());

        }
        else {
            System.out.println("Choix invalide");
        }



    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
