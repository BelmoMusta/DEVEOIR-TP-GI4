package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;

@Repository("chaiseDaoImpl")
public class ChaiseDaoImpl extends GenericDAO implements MaterielDao {

	@Override
	public void add(Materiel materiel) {
		String query = "INSERT INTO chaise (NAME,CODE,STOCK,DISPONIBILITE) VALUES (?,?,?,?)";
		super.add(query, materiel);
	}

	@Override
	public List<Materiel> findAll() {
		String query = "SELECT * FROM chaise;";
		return super.findAll("chaise");
	}

	@Override
	public Materiel findOne(String nom) {
		String query = "SELECT * FROM chaise WHERE name = ?";
		return super.findOne(query, nom);
	}

	@Override
	public void delete(int id) {
		String query1 = "DELETE FROM chaise WHERE id = ?";
		String query2 = "DELETE FROM user_chaise WHERE livre_id = ?";
		super.delete(query1,query2, id);
	}

	@Override
	public void update(int id, Materiel materiel) {
		String query = "UPDATE chaise SET NAME = ?, CODE = ?, STOCK = ?, DISPONIBILITE = ? WHERE ID = ?";
		super.update(query, id, materiel);
	}

	@Override
	protected RowMapper<Materiel> getRowMapper() {
		return new ChaiseRowMapper();
	}

	@Override
	public Materiel findOne(int id) {
		String query = "SELECT * FROM chaise WHERE id = ?";
		return super.findOne(query, id);
	}

	@Override
	public void louerMateriel(String nomMateriel, int userId){
		Materiel materiel = findOne(nomMateriel);
		String query = "Insert into user_chaise (user_id,chaise_id ) values (?,?)";
		super.louerMateriel(query, materiel, userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel, int userId){
		Materiel materiel = findOne(nomMateriel);
		String query = "delete from user_chaise where user_id = ?";
		super.rendreMateriel(query, materiel, userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel) {
		Materiel materiel=findOne(nomMateriel);
		super.markerNonDisponible(materiel);
		update(materiel.getId(), materiel);
	}

}
