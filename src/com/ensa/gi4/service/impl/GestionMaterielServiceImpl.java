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
    	
    	
		
		
		 List<Materiel> list_materiel=materielDao.findAll();

	        System.out.println("List de tout les materiels => (id,nom,code,isDisponible) ");
	        for (Materiel materiel :list_materiel) {
	            System.out.println("id : "+materiel.getId() + "   nome : " + materiel.getName() + " code : " + materiel.getCode() + " disponible : " + materiel.getDisponible());
	        }
        
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

    	try {
    		materielDao.create(materiel);
    		System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
    	
    	 
       
    }

	@Override
	public void chercherMateriel(Long id) {
		
		 System.out.println(materielDao.findone(id));
		
	}

	@Override
	public void supprimerMateriel(Long id) {
		try {
    		materielDao.delete(id);
    		System.out.println("La suppression du matériel " + id + " effectué avec succès !");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
		
	}
	@Override
	public boolean isDisponible(Long id) {
		 Materiel  materiel = materielDao.findone(id);
	      return materiel.getDisponible();
	           

		
	}
	@Override
	public void rendreMaterielIndisponible(Long id) {
		
		try {
			materielDao.rendre_Materiel_indisponible(id);
			System.out.println("Le Materiel est indisponible");
    	}catch(Exception e) {
    		System.out.println("error");
    	}
		
	}

	@Override
	public void allouerMateriel(Long id_M, String dure, Long id_user, String username_user) {
	
		try {
			if(isDisponible(id_M)) {
				materielDao.allouerMateriel(id_M, dure, id_user, username_user);
				System.out.println(username_user + " Vous avez allouer le materiel pour une duree de "+ dure);
			}else {
				System.out.println("Le materiel n'est pas disponible");
				
			}
    	}catch(Exception e) {
    		System.out.println("error");
    	}
		
		
	}

	@Override
	public void rendreMateriel(Long id_M) {
		
		 Materiel  materiel = materielDao.findone(id_M);
	        if(!materiel.getDisponible()){
	            materielDao.rendreMateriel(id_M);
	            System.out.println("Merciiii le matériel est rendu...");
	        }else System.out.println("Vous avez pas déja alloue !!!");
		
	}

	@Override
	public void listeMaterielAlloue(Long id) {
	
		
		 List<Materiel> list_materiel = materielDao.listeMaterielAlloue(id);

	        
	        for (Materiel materiel : list_materiel){
	            System.out.println("nome : "+materiel.getUsername_user()+" nome de materiel : "+materiel.getName()+" code de materiel : "+materiel.getCode());

	        }
		
	}

	@Override
	public void listeToutMaterielAlloue() {
		
		List<Materiel> list_materiel =    materielDao.listeMaterielAlloueAll();

        System.out.println("Username  Materiele ********** Code");
        for (Materiel materiel : list_materiel){
        	if(materiel.getUsername_user() != null) {
            System.out.println("Username : "+materiel.getUsername_user()+" Materiele : "+materiel.getName()+" Code : "+materiel.getCode());
        	}
        }
		
	}

	@Override
	public void modifierMateriel(Long id, String code,String Name) {
		
			materielDao.update(Name, code,id);
			System.out.println("Le materiel est bien modifie");
    	
	}

	

	@Override
	public boolean isExiste(Long id) {
		if(materielDao.findone(id)==null) {
			return false;
		}return true;	
		
	}

	@Override
	public boolean isAlloue(Long id) {
		return materielDao.findone(id).getAlloue();
		
	}

	@Override
	public Materiel findMateriel(Long id) {
		
		return materielDao.findone(id);
	}
}
