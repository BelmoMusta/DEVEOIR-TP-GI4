package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
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
        
        if(materielDao.findAll().size()>0)
        	System.out.println(materielDao.findAll());
			else System.out.println("aucun matériel");
    }
    @Override
	public void ListerMesAllocations(User user) {
		Long idUser=user.getId();
		
		 if(materielDao.MesAllocation(idUser).size()>0)
			 System.out.println(materielDao.MesAllocation(idUser));
			else System.out.println("aucun matériel a été alloué");
	}
    @Override
    public void chercherMateriel(Long id) {
    	if(materielDao.findOne(id)==null) 
    		System.out.println("ce materiel n'existe pas");
    	else 
    	System.out.println(materielDao.findOne(id));
    	
    	
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
    	int nbr=materielDao.ajouterNouveauMateriel(materiel);
    	if(nbr==1) {
		
    	
    		System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    	}
    	
    }
    @Override
    public void alloue(String name,Long idUser,String date) {
    	int nbr=materielDao.allouer(idUser,name,date);
    	if(nbr==1) {
		
    	
    		System.out.println("ce matériel a été bien alloué");
    	}
    	else  System.out.println("ce matériel non disponible ou épuisé");

    }

	@Override
	public void rendreMateriel(Long idUser,Long idMateriel) {
		int nbr=materielDao.rendreMateriel(idUser,idMateriel);
    	if(nbr==1) {
		
    	
    		System.out.println("ce matériel a été bien rendu");
    	}
    	else  System.out.println("vous n'avez pas alloué ce matériel");
	}
	@Override
	public void supprimerMateriel(Long idMateriel) {
		int nbr=materielDao.supprimerMateriel(idMateriel);
    	if(nbr!=0) {
		
    	
    		System.out.println("ce matériel a été bien supprimer");
    	}
    	else  System.out.println("il n'existe pas ce materiel , l'id est incorrect");
	}
	@Override
	public void modifierMateriel(Long idMateriel,String name,String code) {
		int nbr=materielDao.modifierMateriel(idMateriel,name,code);
    	if(nbr!=0) {
		
    	
    		System.out.println("ce matériel a été bien modifier");
    	}
    	else  System.out.println("il n'existe pas ce materiel , l'id est incorrect");
	}
	@Override
	 public void  indisponibleMateriel(Long idMateriel) {
		 int nbr=materielDao.indisponibleMateriel(idMateriel);
	    	if(nbr!=0) {
			
	    	
	    		System.out.println("ce matériel est devenu indisponible!");
	    	}
	    	else  System.out.println("il n'existe pas ce materiel , l'id est incorrect");
	 }
	@Override
	public void  listerMaterielAlloue() {
		if(materielDao.findAllAlloue().size()>0)
		 System.out.println(materielDao.findAllAlloue());
		else System.out.println("aucun matériel a été alloué");
	}
    
}
