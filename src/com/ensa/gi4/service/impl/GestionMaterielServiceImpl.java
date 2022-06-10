package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    static int i_Livre=0;
    static int i_Chaise=0;
    
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
     
    	List<Materiel> liste=materielDao.findAll();
         
        for(int i=0;i<liste.size();i++) {
        	if(liste.get(i).getDispo()==true) {
        	System.out.println("["+liste.get(i).getName()+","+liste.get(i).getCode()+"," + " Disponible" + "]");
        }else {
        	System.out.println("["+liste.get(i).getName()+","+liste.get(i).getCode()+","+ " Non Disponible" + "]");
        }
      }
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
    	
    	if(materiel instanceof Livre) {
    	    materielDao.AjouterMaterial(materiel);
    	}else if(materiel instanceof Chaise) {
    	    materielDao.AjouterMaterial(materiel);
    	}

        System.out.println("L'ajout du materiel " + materiel.getName() + " effectué avec succès !");
    }
	@Override
	public void allouerMateriel(Materiel materiel,User user) {
	          materiel=materielDao.findOneMateriel(materiel.getCode());
	          if(i_Livre<5 && materiel.getName().equals("Livre")) {
	        	  if(materiel.getDispo()==true) {
	        		  materielDao.allouerMateriel(materiel,user);
	        		  i_Livre++;
	        		  materiel.setDispo(false);
	        		  materielDao.ModifierDisponibilite(materiel);
	        		  System.out.println("le livre "+materiel.getCode() + " a été alloué avec succès!!!");
	        	  }else {
	        		  System.out.println("le livre est déjà alloué !!!!");
	        	  }
	          }
	          else if(i_Chaise<5 && materiel.getName().equals("Chaise")) {
	        	  if(materiel.getDispo()==true) {
	        		  materielDao.allouerMateriel(materiel,user);
	        		  i_Chaise++;
	        		  materiel.setDispo(false);
	        		  materielDao.ModifierDisponibilite(materiel);
	        		  System.out.println("la chaise "+materiel.getCode() + " a été alloué avec succès!!!");
	        	  }else {
	        		  System.out.println("la chaise est allouée !!!");
	        	  }
	          }else {
	        	  System.out.println("Vous avez depassez le nombre autorisé d'allocation !!");
	          }
	}

	@Override
	public void RendreMateriel(Materiel materiel,String username) {
		
		materiel=materielDao.findOneMateriel(materiel.getCode());
		Allocation allocation=materielDao.findMaterielAlloue(materiel);
		if(materiel.getDispo()==false && allocation.getUsername().equals(username)) {
			materiel.setDispo(true);
			materielDao.ModifierDisponibilite(materiel);
			materielDao.supprimerMaterielAlloue(materiel);
			System.out.println("le materiel "+ materiel.getCode() + "  a été rendu avec succès!!!!!");}
		else {
			System.out.println("le materiel n'est pas alloué par vous !!!");
		}
	
	}

	@Override
	public void rechercheMateriel(Materiel materiel) {
		
		 materiel = materielDao.RechercheMateriel(materiel.getCode());
		 System.out.println("[ "+materiel.getName()+" " + "," +" " +materiel.getCode()+ " ]");
	}

	@Override
	public void listerMaterielAlloue() {
		
		List<Allocation> liste=materielDao.findAllListAlloue();
        
        for(int i=0;i<liste.size();i++) {
        	System.out.println("[ "+liste.get(i).getUsername()+" "+","+" "+liste.get(i).getCode()+ " ]");
        }
	}
	
	@Override
	public void supprimerMateriel(Materiel materiel) {
		
    	materielDao.SupprimerMateriel(materiel.getCode());
    	System.out.println("le materiel "+ materiel.getCode()+" a été supprimé avec succes !!");
	}

	@Override
	public void modifierMateriel(Materiel materiel, String nouveauCode) {
		
		materielDao.ModifierMateriel(materiel.getCode(), nouveauCode);
		System.out.println("le materiel "+ materiel.getCode()+" a été modifié avec succes !!");
	}

	@Override
	public void listerMaterielAlloueByName(String username) {

		List<Allocation> liste=materielDao.findListAlloueByName(username);
        System.out.println("la liste des materiels allouées par l'utilisateur : \n");
        for(int i=0;i<liste.size();i++) {
        	System.out.println("[ "+liste.get(i).getUsername()+" "+" "+","+liste.get(i).getCode()+ " ]");
        }
		
	}
}
