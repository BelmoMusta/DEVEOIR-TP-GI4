package com.ensa.gi4.service.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.SmartInitializingSingleton;

public class GestionLivreServiceImpl implements GestionMaterielService, SmartInitializingSingleton {
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println("Liste de matériel :\n 3 Livres \n 4 chaises");
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        System.out.println("L'ajout du mat�riel " + materiel.getName() + " effectu�e avec succ�s !");
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("toto");

    }

	@Override
	public void findMateriel(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerMateriel(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierMateriel(int id, String nom, String code) {
		// TODO Auto-generated method stub
		
	}
}
