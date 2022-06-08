package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;

@Repository("chaiseDaoImpl")
public class ChaiseDaoImpl extends GenericDAO implements MaterielDao {

	@Override
	public void add(Materiel materiel) {
		super.add("chaise", materiel);
	}

	@Override
	public List<Materiel> findAll() {
		return super.findAll("chaise");
	}

	@Override
	public Materiel findOne(String nom) {
		return super.findOne("chaise", nom);
	}

	@Override
	public void delete(int id) {
		super.delete("chaise", id);
	}

	@Override
	public void update(int id, Materiel materiel) {
		super.update("chaise", id, materiel);
	}

	@Override
	protected RowMapper<Materiel> getRowMapper() {
		return new ChaiseRowMapper();
	}

	@Override
	public Materiel findOne(int id) {
		return super.findById("chaise", id);
	}

	@Override
	public void louerMateriel(String nomMateriel,int userId) {
		super.louerMateriel("chaise", nomMateriel,userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel,int userId) {
		super.rendreMateriel("chaise", nomMateriel,userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel) {
		super.markerNonDisponible("chaise", nomMateriel);
	}

}
