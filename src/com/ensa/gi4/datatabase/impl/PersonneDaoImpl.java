package com.ensa.gi4.datatabase.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.PersonneDAO;

import com.ensa.gi4.modele.Personne;
@Component 
public class PersonneDaoImpl extends GenericDAO<Personne> implements PersonneDAO{

	private Personne personneConnecte;
	
	@Override
	protected RowMapper<Personne> getRowMapper() {
		// TODO Auto-generated method stub
		return new PersonneRowMapper();
	}

	@Override
	public Personne findPersonne(String nom, String pw) {
		String sql = "Select * from users where name ='"+nom+"' and pw = '"+pw+"'";
		return super.executeQuery(sql);
			
	}
	@Override
	public void ajouterPersonne(String nom, String pw) {
		String sql = "INSERT INTO users (name,pw) VALUES('"+nom+"','"+pw+"')";
		super.inseretUpdateDelete(sql);
	}

	@Override
	public String getRole(String nom) {
		return super.getRole("SELECT role FROM users WHERE name=?", nom);
	}

	@Override
	public boolean allouerMateriel(String nom, String duree) {
		if (personneConnecte != null) {
			String sql = "update materiel set allouer= " + personneConnecte.getId() +
					", duree = '" + duree+ "' where allouer IS NULL and disponible = 'true' and name='"+ nom + "' limit 1";
			if (super.inseretUpdateDelete(sql) !=0) {
				return true;
			}else {
				return false;
			}

		} 
			return false;
	}

}