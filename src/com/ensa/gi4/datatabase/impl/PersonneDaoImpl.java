package com.ensa.gi4.datatabase.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Personne;

@Repository
public class PersonneDaoImpl extends GenericDAO<Personne> implements PersonneDAO {
	private Personne personneConnecte;
	@Autowired
	MaterielDao materielDao;
	
	 @Override
	    public void afterPropertiesSet() {
		 super.afterPropertiesSet();
		 hasherPw();
	 }
	private String getHashPw(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt(10));

	}

	

	private void hasherPw() {
		String sql = "select count (*)  from users";
		int size = super.count(sql);
		for (int j = 1; j <= size; j++) {
			sql = "select pw from users where id = " + j + "";
			sql = "update users set pw ='" + getHashPw(super.extraireString(sql)) + "' where id = " + j + "";
			super.insererOrUpdateOrDelete(sql);

		}
	}

	@Override
	protected RowMapper<Personne> getRowMapper() {
		return new PersonneRowMapper();
	}

	@Override
	public Personne findPersonne(String nom, String pw) {
		String sql = "select * from users where name ='" + nom + "'";	
		List<Personne> listPersonne = super.findAll(sql);
		for (int i = 0; i < listPersonne.size(); i++) {
			if (BCrypt.checkpw(pw, listPersonne.get(i).getPw())) {

				personneConnecte = listPersonne.get(i);
				break;
			}
		}

		return personneConnecte;
	}

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

	@Override
	public boolean creerCompte(String name, String pw, String role) {
		String sql = "select * from users where name='" + name + "' and pw ='" + pw + "'";
		if (super.executeQuery(sql) == null) {
			sql = "insert into users (name, pw,role) values('" + name + "','" + getHashPw(pw) + "','" + role + "')";

			return true;

		}
		return false;
	}

}
