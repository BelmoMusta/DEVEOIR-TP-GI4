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
    public List<Materiel> listerMateriel() {
       return materielDao.findAll();
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        materielDao.ajouterMateriel(materiel);

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }

    @Override
    public void supprimerMateriel(Long id) {
        materielDao.supprimerMateriel(id);
    }

    @Override
    public void modifierNomMateriel(String name, Long id) {
        materielDao.modifierNomMateriel(name,id);
    }

    @Override
    public void modifierCodeMateriel(String code, Long id) {
        materielDao.modifierCodeMateriel(code,id);
    }

    @Override
    public Materiel chercherMateriel(Long id) {
       return materielDao.chercherMateriel(id);
    }

    @Override
    public void rendreMaterielIndisponible(Long id) {
        materielDao.rendreMaterielIndisponible(id);
    }

    @Override
    public void allouerMateriel(String name,boolean b1, boolean b2, Long id) {
        materielDao.allouerMateriel(name,b1,b2,id);
    }

    @Override
    public void rendreMateriel(boolean b1, boolean b2, Long id) {
        materielDao.rendreMateriel(b1,b2,id);
    }

}
