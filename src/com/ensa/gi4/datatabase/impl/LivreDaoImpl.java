package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;

@Repository("livreDaoImpl")
public class LivreDaoImpl extends GenericDAO implements MaterielDao {

	@Override
	public void add(Materiel materiel) {
		String query = "INSERT INTO livre (NAME,CODE,STOCK,DISPONIBILITE) VALUES (?,?,?,?)";
		super.add(query, materiel);
	}

	@Override
	public List<Materiel> findAll() {
		String query = "SELECT * FROM livre;";
		return super.findAll(query);
	}

	@Override
	public Materiel findOne(String nom){
		String query = "SELECT * FROM livre WHERE name = ?";
		return super.findOne(query, nom);
	}

	@Override
	public void delete(int id){
		String query1 = "DELETE FROM livre WHERE id = ?";
		String query2 = "DELETE FROM user_livre WHERE livre_id = ?";
		super.delete(query1,query2, id);
	}

	@Override
	public void update(int id, Materiel materiel){
		String query = "UPDATE livre SET NAME = ?, CODE = ?, STOCK = ?, DISPONIBILITE = ? WHERE ID = ?";
		super.update(query, id, materiel);
	}

	@Override
	public Materiel findOne(int id){
		String query = "SELECT * FROM livre WHERE id = ?";
		return super.findOne(query, id);
	}

	@Override
	public void louerMateriel(String nomMateriel,int userId){
		Materiel materiel=findOne(nomMateriel);
		String query = "Insert into user_livre (user_id,livre_id ) values (?,?)";
		super.louerMateriel(query, materiel,userId);
	}

	@Override
	public void rendreMateriel(String nomMateriel,int userId){
		Materiel materiel=findOne(nomMateriel);
		String query = "delete from user_livre where user_id = ?";
		super.rendreMateriel(query, materiel,userId);
	}

	@Override
	public void markerNonDisponible(String nomMateriel){
		Materiel materiel=findOne(nomMateriel);
		super.markerNonDisponible(materiel);
		update(materiel.getId(), materiel);
	}

	@Override
	protected RowMapper<Materiel> getRowMapper() {
		return new LivreRowMapper();
	}
}
