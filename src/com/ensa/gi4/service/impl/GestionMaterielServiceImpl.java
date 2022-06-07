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
    	materielDao.addMateriel(materiel);
      //  System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }
    @Override
    public void deleteMateriel(Long id) {
    	materielDao.deleteMateriel(id);
    }

	@Override
	public void marquerMaterielIndisponible(Long id) {
		materielDao.marquerMaterielIndisponible(id);
		
	}

	

	@Override
	public void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur) {
		materielDao.allouerMateriel(idMateriel, dure, idUtilisateur);
		
	}

	@Override
	public void rendreMateriel(Long idMateriel) {
		materielDao.rendreMateriel(idMateriel);
		
	}

	@Override
	public void listeMaterielAlloue(Long id) {
		System.out.println(materielDao.listeMaterielAlloue(id));
		
	}

	@Override
	public void listeMaterielAlloueAll() {
		System.out.println(materielDao.listeMaterielAlloueAll());
		
	}

	@Override
	public void modifierMateriel(Long id, String name, String code) {
		materielDao.modifierMateriel(id, name, code);
		
	}

}
