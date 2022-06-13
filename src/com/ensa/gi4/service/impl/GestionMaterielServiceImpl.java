package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    MaterielDao materielDao;
   
    
    @Autowired
    private MessageSource messageSource;
    
    
    @Override
	public void chercherMateriel() {
		System.out.println(messageSource.getMessage("nom",null, Locale.FRANCE));
		System.out.println(materielDao.findByName(scanner.next()));	
    }
    
    @Override
	public void allouerMateriel(int userId) {
		System.out.println(messageSource.getMessage("type",null, Locale.FRANCE));
		String type=scanner.next().toLowerCase();
		if(materielDao.checkdispo(type)){
			System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));
			if(1==materielDao.allouer(userId,scanner.next())){
			System.out.println(messageSource.getMessage("succesAlloue",null, Locale.FRANCE));
			}
			else {
			System.out.println(messageSource.getMessage("indisponible",null, Locale.FRANCE));
			}
		}
		else {
			System.out.println(messageSource.getMessage("indisponible",null, Locale.FRANCE));
			}
		}

    @Override
	public void rendreMateriel(int userId) {
		System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));	
		if(1==materielDao.rendreMateriel(userId,scanner.next())){
			System.out.println(messageSource.getMessage("succesRendu",null, Locale.FRANCE));
        }
		else {
			System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
		}
	}

	@Override
	public void afficherMaterielsAllouesParMoi(int userId) {
		System.out.println(materielDao.findAlloueByMe(userId));
	}
	
    @Override
    public void listerMateriel() {
        System.out.println(materielDao.findAll());
    }
	
	@Override
	public void ajouterNouveauMateriel() {
		System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));
        String name=scanner.next(); 
        if(!materielDao.isExistName(name)) {
        System.out.println(messageSource.getMessage("type",null, Locale.FRANCE));
        String type=scanner.next().toLowerCase(); 
        if(1==materielDao.insert(name,type)) {
        	System.out.println(messageSource.getMessage("succesAjout",null, Locale.FRANCE));
        }
        }
        else {System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
        }
	}
	@Override
	public void modifierMateriel() {
        System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));
        String name =scanner.next();
        if(materielDao.isExistName(name)) {
        System.out.println(messageSource.getMessage("nom_1",null, Locale.FRANCE));
        String newName =scanner.next();
        System.out.println(messageSource.getMessage("type_1",null, Locale.FRANCE));
        String type=scanner.next().toLowerCase(); 
        if(1==materielDao.update(newName,type,name)) {
        	System.out.println(messageSource.getMessage("succesModif",null, Locale.FRANCE));
        }
        }
        else {
			System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
        }
	}

	@Override
	public void supprimerMateriel() {
		System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));
	    String name =scanner.next();
		if(1==materielDao.deleteByName(name)) {
        	System.out.println(messageSource.getMessage("succesSuppression",null, Locale.FRANCE));
        }
		 else {
				System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
	        }
	}

	@Override
	public void marquerIndisponible() {
		System.out.println(messageSource.getMessage("nomMat",null, Locale.FRANCE));
	    String name =scanner.next();
		if(1==materielDao.MarqueIndisponible(name)) {
        	System.out.println(messageSource.getMessage("succesIndisponible",null, Locale.FRANCE));
        }
		else{
			System.out.println(messageSource.getMessage("invalide",null, Locale.FRANCE));
        }
	}
	
	@Override
	public void afficherMaterielsAllouesParUtilisateur() {
		System.out.println(messageSource.getMessage("utilisateur",null, Locale.FRANCE));
		String username=scanner.next();
		System.out.println(materielDao.findAlloueByUser(username)); 
	}

	}

	

