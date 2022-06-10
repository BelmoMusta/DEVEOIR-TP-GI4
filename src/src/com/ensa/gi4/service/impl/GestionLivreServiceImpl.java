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

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("toto");

    }

	@Override
	public void chercherMateriel(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerMateriel(Long id) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur, String usernameUtilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rendreMateriel(Long idMateriel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listeMaterielAlloue(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listeToutMaterielAlloue() {
		// TODO Auto-generated method stub
		
	}

	

	
	@Override
	public boolean isExiste(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlloue(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Materiel findMateriel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rendreMaterielIndisponible(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierMateriel(Long id, String code, String Name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDisponible(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
