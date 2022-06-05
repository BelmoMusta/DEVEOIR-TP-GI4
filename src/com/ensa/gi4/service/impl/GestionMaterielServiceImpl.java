package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void chercherMateriel(long id) {
        System.out.println(materielDao.findMateriel(id));
    }

    @Override
    public void ajouterMateriel(Materiel materiel) {
        System.out.println(materielDao.addMateriel(materiel));
    }

    @Override
    public void supprimerMateriel(int id) {
        System.out.println(materielDao.supprimerMateriel(id));
    }
}
