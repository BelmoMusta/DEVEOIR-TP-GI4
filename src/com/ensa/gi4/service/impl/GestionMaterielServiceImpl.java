package com.ensa.gi4.service.impl;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    @Autowired
    UserDao userDao;


    @Override
    public void listerMateriel() {

        for (Materiel i : materielDao.findAll()){
            System.out.println( i.getName() + " code: "+i.getCode());
        }
    }

    @Override
    public void chercherMateriel() {
        System.out.println("entrer l'id'du materiel que vous cherchez");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();

        try
        {
            System.out.println(materielDao.findOne(ida));
        } catch (Exception e) {
            System.out.println("Le materiel avec l'id "+ ida +" n'existe pas");
        }
    }

    @Override
    public void ajouterNouveauMateriel() {
        Materiel materiel=null;
        System.out.println("entrer L si vous voulez entrer un livre ou C si vous voulez entrer une chaise");
        Scanner type = new Scanner(System.in);
        String typea = type.next();
        System.out.println("entrer le code du materiel");
        String code = type.next();
        if("L".equals(typea)){
            materiel=new Livre();
            materiel.setName("Livre");
            materiel.setCode(code);
            materielDao.insertMateriel(materiel);
        }
        else if("C".equals(typea)){
            materiel=new Chaise();
            materiel.setName("Chaise");
            materiel.setCode(code);
            materielDao.insertMateriel(materiel);
        }
        System.out.println("Materiel Ajouté avec succés");
    }

    @Override
    public void deleteMateriel() {
        System.out.println("entrer l'id'du materiel que vous voulez supprimer");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();
        materielDao.deleteMateriel(ida) ;
        System.out.println("Materiel supprimé avec succés");}

    @Override
    public void updateMateriel() {
        System.out.println("entrer l'id'du materiel que vous voulez modifier");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();
        System.out.println("entrer le nouveau code");
        Scanner code = new Scanner(System.in)
                ;
        String codea = id.next();
        materielDao.updateMateriel(codea,ida);
    }

    @Override
    public void marquerIndispoouDispo() {
        System.out.println("entrer l'id'du materiel que vous voulez marquer disponible");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();
        System.out.println("tapez 0 pour rendre indisponible ou 1 pour rendre disponible");
        Scanner dispo = new Scanner(System.in);
        int dispoa = id.nextInt();
        if(0==dispoa){
            materielDao.marquerDisponible(0,ida);
        }
        else if(1==dispoa){
            materielDao.marquerDisponible(1,ida);
        }

    }

    @Override
    public void allouerMateriel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'du materiel que vous voulez alloué");

        Long idMateriel = scanner.nextLong();
        System.out.println("entrer l'id'd'employee que vous voulez lui alloué le materiel");

        Long idEmployee = scanner.nextLong();


       try{
            userDao.findOne(idEmployee).getPassword();
           int dispo= materielDao.findOne(idMateriel).getDisponible();
           int alloué= materielDao.findOne(idMateriel).getAlloué();
           if(alloué==1){


               System.out.println("materiel déja alloué");
           }
           else if(dispo==1){


               materielDao.allouéMateriel(1,idEmployee,idMateriel);
               System.out.println("materiel avec l'id "+idMateriel+" alloué à l' employee "+idEmployee );
           }

           else{
               System.out.println("materiel indisponible");
           }

       } catch (Exception e) {
           System.out.println("vous ne pouvez pas allouez ce materiel l'un des infos entrées sont incorrectes");
       }

    }

    @Override
    public void rendreMateriel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'du materiel que vous voulez rendre");

        Long idMateriel = scanner.nextLong();



        try{

            int alloué= materielDao.findOne(idMateriel).getAlloué();
            if(alloué==0){


                System.out.println("materiel non alloué pour le rendre");
            }
            else {

materielDao.rendreMateriel(0,null,idMateriel);
                System.out.println("materiel avec l'id "+idMateriel+" rendu");
            }



        } catch (Exception e) {
            System.out.println("materiel avec id " + idMateriel+" non trouvé");
        }
    }

    @Override
    public void rendreMaterielParLui() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'du materiel que vous voulez rendre");

        Long idMateriel = scanner.nextLong();
        try{

            int employee= materielDao.findOne(idMateriel).getEmployee();
            if(employee==GestionMaterielController.id){


                materielDao.rendreMateriel(0,null,idMateriel);
                System.out.println("materiel avec l'id "+idMateriel+" rendu");
            }
            else {


                System.out.println("materiel avec l'id "+idMateriel+" n'est pas alloué par vous");
            }



        } catch (Exception e) {
            System.out.println("materiel avec id " + idMateriel+" non trouvé");
        }
    }

    @Override
    public void allouerMaterielParLui() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'du materiel que vous voulez alloué");

        Long idMateriel = scanner.nextLong();
        try{

            int dispo= materielDao.findOne(idMateriel).getDisponible();
            int alloué= materielDao.findOne(idMateriel).getAlloué();
            if(alloué==1){


                System.out.println("materiel déja alloué");
            }
            else if(dispo==1){


                materielDao.allouéMateriel(1, GestionMaterielController.id,idMateriel);
                System.out.println("materiel avec l'id "+idMateriel+" alloué à vous ");
            }

            else{
                System.out.println("materiel indisponible");
            }

        } catch (Exception e) {
            System.out.println("L'id du materiel est incorrecte");
        }
    }



    @Override
    public void ListeParchacun() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'd'employye sue vous voulez voir a liste des matériels alloués par lui'");

        Long idEmployee = scanner.nextLong();
        List<Materiel> materiel= materielDao.ListeParchacun(idEmployee);
        if(materiel.isEmpty()){
            System.out.println("l'employee avec l'id "+ idEmployee+" n'as alloué aucun materiel");
        }
        else{
            System.out.println("la liste des matériels alloués par l'employee avec l'id "+ idEmployee);
            for (Materiel i : materiel){
                System.out.println( i.getName() + " code: "+i.getCode());
            }
        }


    }

    @Override
    public void ListeParLui() {
        List<Materiel> materiel= materielDao.ListeParLui();
        if(materiel.isEmpty()){
            System.out.println("vous n'avez alloué aucun materiel");
        }
        else{
            System.out.println("la liste des matériels alloués par vous est : ");
            for (Materiel i : materiel){
                System.out.println( i.getName() + " code: "+i.getCode());
            }
        }
    }
}
