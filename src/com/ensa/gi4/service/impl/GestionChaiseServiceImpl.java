package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.SmartInitializingSingleton;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

public class GestionChaiseServiceImpl implements GestionMaterielService, SmartInitializingSingleton{
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
	       // System.out.println("toto");
	    }

		@Override
		public void chercherMateriel(Long code) {
			System.out.println("chercher chaise");
		}

		@Override
		public void deleteMateriel(Long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void marquerMaterielIndisponible(Long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur) {
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
		public void listeMaterielAlloueAll() {
			// TODO Auto-generated method stub
			
		}

		
}
