package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;
@Component 
public class PersonneDaoImpl extends GenericDAO<Personne> implements PersonneDAO{

	private Personne personneConnecte;
	
	
	// hasher les mots de passe 
	 @Override
	    public void afterPropertiesSet() {
		 super.afterPropertiesSet();
		 hasherPw();
	 }
	private String getHashPw(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt(10));

	}

	private boolean verifierPW(String pw, String hashPw) {
		if (BCrypt.checkpw(pw, hashPw)) {
			return true;
		} else {
			return false;
		}
	}

	private void hasherPw() {
		String sql = "select count (*)  from users";
		int size = super.count(sql);
		for (int j = 1; j <= size; j++) {
			sql = "select pw from users where id = " + j + "";
			sql = "update users set pw ='" + getHashPw(super.extraireString(sql)) + "' where id = " + j + "";
			super.inseretUpdateDelete(sql);

		}
	}
	
	
	
	@Override
	protected RowMapper<Personne> getRowMapper() {
		// TODO Auto-generated method stub
		return new PersonneRowMapper();
	}

	@Override
	public Personne findPersonne(String nom, String pw) {
		String sql = "Select * from users where name ='"+nom+"'";
		List<Personne> listPersonne = super.findAll(sql);
		for (int i = 0; i < listPersonne.size(); i++) {
			if (verifierPW(pw, listPersonne.get(i).getPw())) {

				personneConnecte = listPersonne.get(i);
				break;
			}
		}

		return personneConnecte;
			
	}
	
	
	@Override
	public void ajouterPersonne(String nom, String pw) {
		String sql = "INSERT INTO users (name,pw) VALUES('"+nom+"','"+ getHashPw(pw) +"')";
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

	@Override
	public boolean rendreMateriel(String nom) {
		if (personneConnecte != null) {
			String sql = "update materiel set allouer= NULL, duree = 'NULL' where allouer = "+personneConnecte.getId()+" and name='"+ nom + "' limit 1";
			if (super.inseretUpdateDelete(sql) !=0) {		
				return true;
			}else {
				System.out.println("false");
				return false;
			}

		} 
			return false;
	}

	@Override
	public Personne getPersonneConnecte() {
		return personneConnecte;
	}
	

	@Override
	public String determinerRole() {
		if (personneConnecte != null) {
			return personneConnecte.getRole();
		}
		return "";
	}
	
	
	

}