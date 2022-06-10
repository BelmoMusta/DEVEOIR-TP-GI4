package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    
    @Autowired
    AllocationDao allocationDao;
    
    
    
	@Override
    public void init() {
        System.out.println("inititialisation du service");
    }


	@Override
	public void listerMateriel() {
		for(Materiel a : materielDao.findAll()) {
			System.out.println("Id : " + a.getId());					
			System.out.println("nom materiel : " + a.getName());
			System.out.println("type : " + a.getCode());
			System.out.println("etat : " + a.getEtat());
			System.out.println("---------------------");					
		}
	}


	@Override
	public void ajouterNouveauMateriel(Materiel materiel) {
    	materielDao.addMateriel(materiel);
	}


	@Override
	public void chercherMateriel(int ID) {
		 System.out.println(materielDao.findOne(ID));
	}


	@Override
	public void modifierMateriel(int ID, String name, String type) {
		materielDao.modifierMateriel(ID, name, type);
	}


	@Override
	public void supprimerMateriel(int ID) {
    	materielDao.deleteMateriel(ID);
	}


	@Override
	public void marquerMaterielIndisponible(int ID) {
		materielDao.marquerMaterielIndisponible(ID);		
	}


	@Override
	public void allouerMateriel(int idMateriel, String date1, String date2, int idUtilisateur) {
		
	    Date dateDebut = Date.valueOf(date1);  
	    Date dateFin = Date.valueOf(date2);  

		boolean bool = materielDao.allouerMateriel(idMateriel, dateDebut, dateFin, idUtilisateur);
		if(!bool) {
			System.out.println("Le materiel choisie n'est pas disponible");
		}
	}


	@Override
	public void rendreMateriel(int ID) {
		materielDao.rendreMateriel(ID);	
	}


	@Override
	public void listeMaterielAlloue(int ID) {
		List<Allocation> list = allocationDao.listeMaterielAlloueAll();
		for(Allocation a : list) {
			System.out.println("nom materiel : " + a.getMaterielName());
			System.out.println("type : " + a.getType());
			System.out.println("date de debut : " + a.getDateDebut());
			System.out.println("date de fin : " + a.getDateFin());					
			System.out.println("---------------------");					
		}	
	}


	@Override
	public void listeMaterielAlloueAll() {
		List<Allocation> list = allocationDao.listeMaterielAlloueAll();
		for(Allocation a1 : list) {
			System.out.println("Nom : " + a1.getUserName());
			System.out.println("Role : " + a1.getRole());
			for(Allocation a2 : list) {
				if(a1.getUserName().equals(a2.getUserName())) {
					System.out.println("	nom materiel : " + a2.getMaterielName());
					System.out.println("	type : " + a2.getType());
					System.out.println("	date de debut : " + a2.getDateDebut());
					System.out.println("	date de fin : " + a2.getDateFin());					
				}
				System.out.println("	---------------------");					
			}
			System.out.println("------------------------------------------");					
			break;
		}
	}


}
