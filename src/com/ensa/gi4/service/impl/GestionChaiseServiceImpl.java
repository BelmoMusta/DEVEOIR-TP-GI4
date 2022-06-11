package com.ensa.gi4.service.impl;

import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

@Service("gestionChaiseService")
public class GestionChaiseServiceImpl implements GestionMaterielService,SmartInitializingSingleton{

	@Autowired
	@Qualifier("chaiseDaoImpl")
	private MaterielDao chaiseDao;
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("chaise service generated");
	}

	@Override
	public void add(Materiel materiel) {
		chaiseDao.add(materiel);
	}

	@Override
	public List<Materiel> findAll() {
		return chaiseDao.findAll();
	}

	@Override
	public Materiel findOne(String nom) {
		return chaiseDao.findOne(nom);
	}

	@Override
	public void delete(int id) {
		chaiseDao.delete(id);
	}

	@Override
	public void update(int id, Materiel materiel) {
		chaiseDao.update(id, materiel);
	}

	@Override
	public void louerMateriel(String nomMateriel,int userId) {
		chaiseDao.louerMateriel(nomMateriel,userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel,int userId) {
		chaiseDao.rendreMateriel(nomMateriel,userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel) {
		chaiseDao.markerNonDisponible(nomMateriel);
	}

}
