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
        System.out.println(materielDao.findAll());
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        try {
            materielDao.ajouterUnMateriel(materiel);
            System.out.println("L'ajout du matÃ©riel " + materiel.getName() + " effectuÃ© avec succÃ¨s !");
        }catch(Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void supprimerUnMaterial(int id) {
        materielDao.supprimer(id);
        System.out.println("Le materiel est supprimer!!");
    }

    @Override
    public boolean checkAvantSupprimer(int id) {
        if(materielDao.findOne(id)==null) {
            return false;
        }
        return true;
    }

    @Override
    public void modifierUnMateriel(int id, String code, String name) {
        try {
            materielDao.modifier(id, code,name);
            System.out.println("Le materiel est modifie");
        }catch(Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void chercherMateriel(int id) {
        System.out.println(materielDao.findOne(id));
    }

    @Override
    public void materielIndisponible(int id) {
        materielDao.indisponible(id);
        System.out.println("Vous avez marqué que le Materiel est indisponible!!");
    }

    @Override
    public void materialAlloue(int id, String duree,int id_user, String username) {
        materielDao.allouer(id, duree,id_user,username);
        System.out.println( username + " Vous avez allouer le materiel pour un duree de "+ duree);
    }

    @Override
    public void renderMateriel(int id) {
        materielDao.render(id);
        System.out.println("Le matériel est rendu!!!");
    }

    @Override
    public void AllMaterielAlloue(int user_id) {
        System.out.println( materielDao.listeMaterielAlloue(user_id));
    }

    @Override
    public void allAllloue() {
        System.out.println(materielDao.listeAlloue());
    }


}
