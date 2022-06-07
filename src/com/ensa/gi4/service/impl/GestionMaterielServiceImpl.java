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
    public void chercherMateriel(Long id) {

        System.out.println(materielDao.findOne(id));

    }


    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        System.out.println(materielDao.addMateriel(materiel));

    }

    @Override
    public void supprimerMateriel(Long id) {

        System.out.println(materielDao.deleteMateriel(id));

    }

}
