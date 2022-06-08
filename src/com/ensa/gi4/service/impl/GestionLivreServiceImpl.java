package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("gestionLivreService")
public class GestionLivreServiceImpl implements GestionMaterielService, SmartInitializingSingleton {

	@Autowired
	@Qualifier("livreDaoImpl")
	private MaterielDao livreDao;

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("livre service generated");
	}

	@Override
	public void add(Materiel materiel) {
		livreDao.add(materiel);
	}

	@Override
	public List<Materiel> findAll() {
		return livreDao.findAll();
	}

	@Override
	public Materiel findOne(String nom) {
		return livreDao.findOne(nom);
	}

	@Override
	public void delete(int id) {
		livreDao.delete(id);
	}

	@Override
	public void update(int id, Materiel materiel) {
		livreDao.update(id, materiel);
	}

	@Override
	public void louerMateriel(String nomMateriel, int userId) {
		livreDao.louerMateriel(nomMateriel, userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel, int userId) {
		livreDao.rendreMateriel(nomMateriel, userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel) {
		livreDao.markerNonDisponible(nomMateriel);
	}

}
