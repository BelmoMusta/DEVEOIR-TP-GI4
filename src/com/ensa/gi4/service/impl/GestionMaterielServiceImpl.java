package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        List<Materiel> list=materielDao.findAll();

        System.out.println("ID ********** Materiel *********** Code *********** Dispo ");
        for (Materiel l :list) {
            System.out.println(l.getId() + " ********** " + l.getName() + " *********** " + l.getCode() + "***********" + l.getDisponible());
        }
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        try {
            materielDao.ajouterUnMateriel(materiel);
            System.out.println("Le materiel est bien ajoutee!!!");

        }catch(Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void supprimerUnMaterial(int id) {

    }

    @Override
    public boolean checkAvantSupprimer(int id) {
        return false;
    }

    @Override
    public void modifierUnMateriel(int id, String code, String name) {

    }

    @Override
    public void chercherMateriel(int id) {

    }

    @Override
    public void materielIndisponible(int id) {

    }

    @Override
    public void materialAlloue(int id, String duree, int id_user, String username) {
        materielDao.allouer(id, duree,id_user,username);
        System.out.println( username + " Vous avez allouer le materiel pour une duree de "+ duree);
    }


    @Override
    public void renderMateriel(int id) {
        Materiel  m = materielDao.findOne(id);
        if(m.getAlloue()){
            materielDao.render(id);
            System.out.println("Le matériel est rendu!!!");
        }else System.out.println("Vous avez pas déja alloue!!!");
    }

    @Override
    public Boolean isDispo(int id) {
        Materiel  m = materielDao.findOne(id);
        if (m.getDisponible()){
            return true;
        }else return false;
    }

    @Override
    public void AllMaterielAlloue(int user_id) {
        List<Materiel> list = materielDao.listeMaterielAlloue(user_id);

        System.out.println("Username ********** Materiele ********** Code");
        for (Materiel l : list){
            System.out.println(l.getUsername()+" ********** "+l.getName()+" ********** "+l.getCode());

        }
    }


    @Override
    public void allAllloue() {
        List<Materiel> list =    materielDao.listeAlloue();

        System.out.println("Username ********** Materiele ********** Code");
        for (Materiel l : list){
            System.out.println(l.getUsername()+" ********** "+l.getName()+" ********** "+l.getCode());

        }
    }
}
