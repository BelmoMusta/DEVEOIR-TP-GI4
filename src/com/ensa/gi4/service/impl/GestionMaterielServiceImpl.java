package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    @Autowired
    UserDao userDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        for (Materiel materiel : materielDao.findAll()) {
            System.out.println("list of all material");
            System.out.println(materiel.getName() + " code: " + materiel.getCode());
        }
    }

    @Override
    public void ajouterNouveauMateriel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour ajouter un Livre entrer : 1, pour un Chaise entrer : 2");
        int saisir = scanner.nextInt();

        if(saisir == 1)
        {
            System.out.println("nom : ");
            String nom = scanner.next();
            System.out.println("code : ");
            String code = scanner.next();
            Livre l = new Livre();
            l.setName(nom);
            l.setCode(code);
            materielDao.aadd(l);
            System.out.println("L'ajout du matériel " + l.getName() + " effectué avec succès !");
        }
        else if(saisir == 2)
        {
            System.out.println("nom : ");
            String nom = scanner.next();
            System.out.println("code : ");
            String code = scanner.next();
            Chaise c = new Chaise();
            c.setName(nom);
            c.setName(code);
            materielDao.aadd(c);
            System.out.println("L'ajout du matériel " + c.getName() + " effectué avec succès !");
        }

    }

    @Override
    public void search() {
        System.out.println("entrer l'id'du materiel que vous cherchez");
        Scanner id = new Scanner(System.in);
        Long iD = id.nextLong();

        try
        {
            System.out.println(materielDao.findOne(iD));
        } catch (Exception e) {
            System.out.println("Le materiel avec l "+ iD +" n'existe pas");
        }
    }

    @Override
    public void delet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom de materiel a supprimer : ");
        String code = scanner.next();
        materielDao.deleteMateriel(code);
        System.out.println("Materiel avec le code  " + code + " Supprimé");
    }
    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le code de materiel a modifier : ");
        String code = scanner.next();


            System.out.println("code : ");
            String newNmae = scanner.next();
            materielDao .updateMateril(code,newNmae);
        System.out.println("Materiel avec le code  " + newNmae + " est modiffier");



    }

    @Override
    public void allouerMateriel() {
        System.out.println("Entrer le id  de materiel a allouer : ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        System.out.println("enter your id plase");

        Long userId = scanner.nextLong();

        try{
            userDao.findOne(userId).getPassword();
            int dispo= materielDao.findOne(id).getDisponible();
            int epuise= materielDao.findOne(id).getEpuise();
            int alloué= materielDao.findOne(id).getAlloué();
            if(alloué==1){
                
                System.out.println("materiel déja alloué");
            }
            else if(dispo==1 && epuise==1){


                System.out.println(materielDao.allouerMateriel(1,userId,id));
                System.out.println("out od stock");

            }
            else if(dispo==1 || epuise==0){


                System.out.println(materielDao.allouerMateriel(1,userId,id));
                System.out.println("materiel indisponible");

            }
            else if(dispo==0 || epuise==1){


                System.out.println(materielDao.allouerMateriel(1,userId,id));
                System.out.println("materiel est epuise");

            }

            else{
                System.out.println("materiel disponible");
            }

        } catch (Exception e) {
            System.out.println(" des infos entrées sont incorrectes");
        }

    }


    @Override
    public void marquerIndispoouDispo() {
        System.out.println("entrer l'id'du materiel que vous voulez marquer disponible");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();
        System.out.println("tapez 0 pour rendre indisponible ou 1 pour rendre disponible");
        int matdispo = id.nextInt();
        if(0==matdispo){
            materielDao.marquerDisponible(0,ida);
            System.out.println("le materiel avec id " + id + "est indisponible ");
        }
        else if(1==matdispo){
            materielDao.marquerDisponible(1,ida);
            System.out.println("le materiel avec id " + id + "est disponible ");
        }



    }

    @Override
    public void materielEpuise() {
        System.out.println("entrer l'id'du materiel epuise");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        System.out.println("tapez 0 pour si le materil est epuise ou 1 si non ");
        int matepuise = scanner.nextInt();
        if(0==matepuise){
            materielDao.epuise(0,id);
            System.out.println("le materiel avec id " + id + "est epuise ");
        }
        else if(1==matepuise){
            materielDao.epuise(1,id);
        }

    }

    @Override
    public void renderMateriel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("entrer l'id'du materiel que vous voulez rendre");

        Long idMateriel = scanner.nextLong();
        try{

            int alloué= materielDao.findOne(idMateriel).getAlloué();
            if(alloué==0){


                System.out.println("materiel non alloué pour le rendre");
            }
            else {

                materielDao.renderMaterial(0,null,idMateriel);
                System.out.println("materiel avec l'id "+idMateriel+" rendu");
            }



        } catch (Exception e) {
            System.out.println("materiel avec id " + idMateriel+" non trouvé");
        }
    }

    @Override
    public void listerMatAllouer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your  user id plase");
        Long userId = scanner.nextLong();
        try {
            userDao.findOne(userId).getPassword();
            for (Materiel materiel : materielDao.allMatAlloue(userId)) {
                System.out.println("list of all material allouer par user avec id " +userId);
                System.out.println( "name" + materiel.getName() + " code: " + materiel.getCode());
            }
        } catch (Exception e) {
            System.out.println(" des infos entrées sont incorrectes");
        }



    }


    void sortirDeLApplication() {
        System.exit(0);
    }
}

