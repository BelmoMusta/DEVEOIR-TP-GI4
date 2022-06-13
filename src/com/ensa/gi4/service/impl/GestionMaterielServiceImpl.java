package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.Iterator;
import java.util.List;

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
    	List<Materiel> listMateriel;
		listMateriel=materielDao.findAll();
		Iterator iterator = listMateriel.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+"\n");
		}
    }

	@Override
	public void chercherMateriel(Long id) {
		 System.out.println(materielDao.findOne(id));
	}

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
    	try {
    		materielDao.addMateriel(materiel);
    		System.out.println("L'ajout du mat√©riel " + materiel.getName() + " effectu√© avec succ√®s !");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
    }
    @Override
    public void deleteMateriel(Long id) {
    	try {
    		materielDao.deleteMateriel(id);
    		System.out.println("La suppression du mat√©riel " + id + " effectu√© avec succ√®s !");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
    }

	@Override
	public void marquerMaterielIndisponible(Long id) {
		try {
			materielDao.marquerMaterielIndisponible(id);
			System.out.println("Le materiel est marquÈ indisponible");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
	}

	@Override
	public boolean isDisponible(Long id) {
			return materielDao.findOne(id).isDisponible();	
	}
	
	@Override
	public void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur, String usernameUtilisateur) {
		try {
			if(isDisponible(idMateriel)) {
				materielDao.allouerMateriel(idMateriel, dure, idUtilisateur, usernameUtilisateur);
				System.out.println("Le materiel est allouÈ");
			}else {
				System.out.println("Le materiel n'est pas disponible");
			}
    	}catch(Exception e) {
    		System.out.println("error");
    	}
	}

	@Override
	public void rendreMateriel(Long idMateriel) {
		try {
			if(materielDao.findOne(idMateriel).isAlloue()) {
				materielDao.rendreMateriel(idMateriel);
				System.out.println("Le materiel est rendÈ");
			}else {
				System.out.println("Le materiel n'est pas allouÈ");
			}
    	}catch(Exception e) {
    		System.out.println("error");
    	}
	}

	@Override
	public void listeMaterielAlloue(Long id) {
		try {
			List<Materiel> listMateriel;
			listMateriel=materielDao.listeMaterielAlloue(id);
			if(listMateriel.size()!=0) {
			Iterator iterator = listMateriel.iterator();
			while(iterator.hasNext()) {
				System.out.print(iterator.next()+"\n");
			}
			}else {
				System.out.println("Vous n'avez pas alloue");
			}
		}catch(Exception e) {
    		System.out.println("error");
    	}
		
	}

	@Override
	public void listeMaterielAlloueAll() {
		try {
		List<Materiel> listMateriel;
		listMateriel=materielDao.listeMaterielAlloueAll();
		if(listMateriel.size()!=0) {
		Iterator iterator = listMateriel.iterator();
		while(iterator.hasNext()) {
				System.out.print(iterator.next()+"\n");
			}
		}else {
			System.out.println("ni materiel allouÈ");
		}}
		catch(Exception e) {
    		System.out.println("error");
    	}	
	}

	@Override
	public void modifierMateriel(Long id, String code) {
		try {
			materielDao.modifierMateriel(id, code);
			System.out.println("Le materiel est bien modifie");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
		
	}

	@Override
	public boolean isExiste(Long id) {
		if(materielDao.findOne(id)==null) {
			return false;
		}return true;	
	}

	@Override
	public boolean isAlloue(Long id) {
		return materielDao.findOne(id).isAlloue();
	}

	@Override
	public Materiel findMateriel(Long id) {
			return materielDao.findOne(id);
	}



}
