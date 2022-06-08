package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.ArrayList;
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
    	 ArrayList<Materiel> materiel = new ArrayList();
    	materiel = (ArrayList<Materiel>) materielDao.findAllMateriel();
       
       for(int i=0; i < materiel.size();i++) {
    	   System.out.println(materiel.get(i));
       }
    }
    
    @Override
	public void findOneMateriel(long id) {
		 if(materielDao.findOneMateriel(id)!=null ) {
			 System.out.println("le materiel que vous cherchez est '"+materielDao.findOneMateriel(id).getName()+ "' de code '"+materielDao.findOneMateriel(id).getCode()+"'");
		 }
		 else {
			 System.out.println("Ce materiel n'existe pas");
		 }
	}
    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
         if(materielDao.ajouterMateriel(materiel)) {
        	 System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
         }else {
        	         System.out.println("L'ajout du matériel " + materiel.getName() + " n'est pas effectu� !");
         }
    }

	@Override
	public void supprimerMateriel(int id) {
		if(materielDao.supprimmerMateriel(id)) {
			System.out.println("La suppression a  �t� bien effectu�e");
		}else {
			System.out.println("une erreur a �t� survenu !");
		}
		
	}
  
	public void modifierMateriel(int id, String nom, String code) {
		if(materielDao.modifierMateriel(id, nom, code)) {
			System.out.println("La modification a bien �t� effectu�e");
		}else {
			System.out.println("une erreur a �t� survenu veuillez r�essayer � nouveau!");
		}
		
	}
}
