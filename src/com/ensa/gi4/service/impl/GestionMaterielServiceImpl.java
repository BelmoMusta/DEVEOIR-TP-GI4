package com.ensa.gi4.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    // bd goes here
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
    	for(Materiel m : materielDao.findAll()) {
            System.out.println("Id : " + m.getId());
            System.out.println("Nom : " + m.getName() + ", code : " + m.getCode());

    	}
    }
    
    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        // à compléter
    	if(materielDao.addOne(materiel)== 1) {
            System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    	}else System.out.println("Erreur d'ajout du matériel");
    }

	@Override
	public void suppMateriel(String code) {
		// TODO Auto-generated method stub
		if(materielDao.deleteOne(code)== 1) {
            System.out.println("Supprimé avec succès !");
    	}else System.out.println("Erreur lors de la suppresion");
		
	}

	@Override
	public boolean findOne(String code) {
		// TODO Auto-generated method stub
		List<Materiel> list = new ArrayList<Materiel>();
		list = materielDao.findAll() ;
		for(Materiel m : list) {
			if(m.getCode().equals(code))return true;
		}
		
		return false;
	}

	@Override
	public int marquerDispo(boolean dispo, String code) {
		// TODO Auto-generated method stub
		return materielDao.changeAvailability(dispo,code);
	}

	@Override
	public int modifierMateriel(String code, String nom) {
		// TODO Auto-generated method stub
		return materielDao.updateName(code,nom);
	}

	@Override
	public int allouerMateriel(int userId, String code) {
		// TODO Auto-generated method stub
		for ( Materiel m : materielDao.findAll() ) {
			if(m.getCode().equals(code) && m.isDispo()== true) {
				materielDao.changeAvailability(false, m.getCode());
		return materielDao.allocateMateriel(userId, code);
		}
			}
		return 0;
	}

	@Override
	public int rendreMateriel(int userId, String code) {
		// TODO Auto-generated method stub
		materielDao.changeAvailability(false, code);
		return materielDao.returnMateriel(userId, code);
	}

	@Override
	public List<Materiel> materielAlloués(int userId) {
		// TODO Auto-generated method stub
		return materielDao.findAllocatedMateriel(userId);
	}

	@Override
	public List<Materiel> toutMateriaux() {
		// TODO Auto-generated method stub
		return materielDao.findAll();
	}


}
