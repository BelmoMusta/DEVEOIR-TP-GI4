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

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }

	
}
