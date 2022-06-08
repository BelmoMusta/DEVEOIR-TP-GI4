package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;

@Repository("livreDaoImpl")
public class LivreDaoImpl extends GenericDAO implements MaterielDao {

	@Override
	public void add(Materiel materiel) {
		super.add("livre", materiel);
	}

	@Override
	public List<Materiel> findAll() {
		return super.findAll("livre");
	}

	@Override
	public Materiel findOne(String nom) {
		return super.findOne("livre", nom);
	}

	@Override
	public void delete(int id) {
		super.delete("livre", id);
	}

	@Override
	public void update(int id, Materiel materiel) {
		super.update("livre", id, materiel);
	}

	@Override
	protected RowMapper<Materiel> getRowMapper() {
		return new LivreRowMapper();
	}

	@Override
	public Materiel findOne(int id) {
		return super.findById("livre", id);
	}

	@Override
	public void louerMateriel(String nomMateriel,int userId) {
		super.louerMateriel("livre", nomMateriel,userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel,int userId) {
		super.rendreMateriel("livre", nomMateriel,userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel) {
		super.markerNonDisponible("livre", nomMateriel);
	}

}
