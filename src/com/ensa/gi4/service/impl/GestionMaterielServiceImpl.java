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
        List<Materiel> list = materielDao.findAll();
        System.out.println("ID | Materiel | Code | Dispo | Type ");
        for (Materiel materiel : list) {
            System.out.println(materiel.getId() + " | " + materiel.getName() + " | " + materiel.getCode() + " | " + materiel.getDispo() + " | " + materiel.getType());
        }
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        try {
            materielDao.ajouterUnMateriel(materiel);

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void supprimerUnMaterial(int id) {
        materielDao.supprimer(id);
        System.out.println("Le materiel est supprimer.");
    }

    @Override
    public boolean checkAvantSupprimer(int id) {
        if (materielDao.findOne(id) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void modifierUnMateriel(int id, String code, String name) {
        try {
            materielDao.modifier(id, code, name);
            System.out.println("Le materiel est modifie.");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void chercherMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if (materielDao.findOne(id) != null) {
            System.out.println(materiel.getId() + " | " + materiel.getName() + " | " + materiel.getCode() + " | " + materiel.getDispo() + " | " + materiel.getType());
        } else
            System.out.println("le materiel n'est existe pas.");

    }

    @Override
    public void materielIndisponible(int id) {
        materielDao.indisponible(id);
        System.out.println(" le Materiel est indisponible.");
    }

    @Override
    public void materialAlloue(int id, String duree, int id_user, String username) {

        materielDao.allouer(id, duree, id_user, username);
        System.out.println(username + " est allouer le materiel pour une duree de " + duree);

    }

    @Override
    public void renderMateriel(int id) {
        Materiel m = materielDao.findOne(id);
        if (m.getAlloue()) {
            materielDao.render(id);
            System.out.println("Le matériel est rendu.");
        } else System.out.println("Vous avez pas déja alloue.");


    }

    @Override
    public Boolean isDispo(int id) {
        Materiel m = materielDao.findOne(id);
        if (m.getDispo()) {
            return true;
        } else return false;
    }

    @Override
    public void AllMaterielAlloue(int user_id) {
        List<Materiel> list = materielDao.listeMaterielAlloue(user_id);

        System.out.println("Username | Materiel | Code");
        for (Materiel materiel : list) {
            System.out.println(materiel.getUsername() + " | " + materiel.getName() + " | " + materiel.getCode());

        }


    }

    @Override
    public void allAllloue() {
        List<Materiel> list = materielDao.listeAlloue();

        System.out.println("Username | Materiel | Code");
        for (Materiel materiel : list) {
            System.out.println(materiel.getUsername() + " | " + materiel.getName() + " | " + materiel.getCode());

        }


    }


}
