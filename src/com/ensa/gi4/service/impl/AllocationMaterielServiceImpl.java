package com.ensa.gi4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllouer;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AllocationMaterielService;

@Service
public class AllocationMaterielServiceImpl implements AllocationMaterielService {
	
	@Autowired
	MaterielDao materielDao; 
	@Autowired
	MaterielAllouerDao materielAllouerDao;
	@Autowired
	UserDao userDao;

	@Override
	public Materiel chercherMateriel(TypeMateriel typeMateriel ,String code) {
		return materielDao.findOne(code, typeMateriel.toString());
	}

	@Override
	public void allouerMateriel(TypeMateriel typeMateriel, String code, User user) {
		int test =  materielDao.allouerMateriel(code, typeMateriel.toString(), user); 
		if(test == 0 ) {
			System.out.println("----------------------------------------------------");
			System.out.println("Desolé le materiel n'est plus allouable ! ");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Allocation du materiel reussit !");
			System.out.println("----------------------------------------------------");
		}
			
		
	}

	@Override
	public void rendreMaterielAlloue(TypeMateriel typeMateriel, String code, User user) {
		int test =  materielDao.rendreMateriel(code, typeMateriel.toString(), user); 
		
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println("Echec de'operation !");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Materiel alloué rendu !");
			System.out.println("----------------------------------------------------");
		}
	}

	@Override
	public List<Materiel> listeMaterielAlloue(User user) {
			
		return materielDao.listeMaterielAlloue(user); 
	}

	@Override
	public List<Materiel> listeMateriel() {
		return materielDao.findAll();
	}

	@Override
	public void listeMaterielAlloueParUser() {
		List<User> users = userDao.findAll(); 
		List<MaterielAllouer> materielAllouers =  materielAllouerDao.listeMaterielAlloueParUser(); 
		
		System.out.println("------------\tMateriel alloueé par utilisateur\t------------");
		System.out.println("----------------------------------------------------");
		for (User user : users) {
			System.out.println("Utilisateur : "+ user);
			for (MaterielAllouer materielAllouer : materielAllouers) {
				if (user.getName()== materielAllouer.getUser().getName() && user.getRole() == materielAllouer.getUser().getRole()) {
					System.out.println(materielAllouer.getMateriel());
				}
			}
			
			System.out.println("----------------------------------------------------");
		}
	}

}
