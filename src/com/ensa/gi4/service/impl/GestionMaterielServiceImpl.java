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
	public void chercherMateriel(Long code) {
		 System.out.println(materielDao.findOne(code));
	}

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
    	materielDao.addMateriel(materiel);
      //  System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }
    @Override
    public void deleteMateriel(Long id) {
    	materielDao.deleteMateriel(id);
    }

}
