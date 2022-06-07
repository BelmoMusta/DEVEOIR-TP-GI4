package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;
import java.util.*;
@Component
public class PersonneDaoImpl extends GenericDAO<Personne> implements PersonneDAO {
	private Personne personneConnecte;
	@Autowired
	MaterielDao materielDao;

	@Override
	protected RowMapper<Personne> getRowMapper() {
		return new PersonneRowMapper();
	}

	@Override
	public Personne findPersonne(String nom, String pw) {

		String sql = "Select * from users where name ='" + nom + "' and pw = '" + pw + "'";
		personneConnecte = super.executeQuery(sql);
		return personneConnecte;
	}

	/*
	 * @Override public void allouerMateriel(String code, String duree) {
	 * 
	 * String sql = "insert into allouer (materiel_id,user_id,duree) values(" +
	 * materielDao.codeMatereielExiste(code).getId() + "," +
	 * personneConnecte.getId() + ",'" + duree + "')";
	 * super.insererOuUpdateOrDelete(sql); // return personneConnecte;
	 * 
	 * }
	 */
	@Override
	public boolean allouerMateriel(String nom, String duree) {
		if (personneConnecte != null) {
			String sql = "update materiel set allouer= " + personneConnecte.getId() +
					", duree = '" + duree+ "' where allouer IS NULL and disponible = true and name='" +
					nom + "' limit 1";
//sql= "update materiel set allouer = 1 ";
			if (super.insererOrUpdateOrDelete(sql) !=0) {
				return true;
			}else {
				return false;
			}

		} 
			return false;
		

	}

	/*@Override
	public void rendreMateriel(String code) {
		if (materielDao.codeMatereielExiste(code) != null) {
			String sql = "delete from allouer where materiel_id=" + materielDao.codeMatereielExiste(code).getId()
					+ "and user_id=" + personneConnecte.getId() + "";
			super.insererOrUpdateOrDelete(sql);
		}
	}*/

	/*@Override
	public boolean verifierExistanceAllocation(String code) {
		if (materielDao.codeMatereielExiste(code) != null) {
			String sql = "select count (*)  from allouer where  materiel_id="
					+ materielDao.codeMatereielExiste(code).getId() + "" + "and user_id=" + personneConnecte.getId()
					+ "";
			if (super.count(sql) != 0) {
				return true;
			}
		}
		return false;
	}*/



	@Override
	public boolean rendreMateriel(int id) {
		String sql  = "update materiel set allouer= null, duree = null where allouer="+personneConnecte.getId()+" and id="+id+"";
		if (super.insererOrUpdateOrDelete(sql) !=0) {
			return true;
		}else {
			return false;
		}

	 
		
	}

	public Personne getPersonneConnecte() {
		return personneConnecte;
	}

	@Override
	public String determinerRole() {
		if(personneConnecte!=null) {
			return personneConnecte.getRole();
		}
		return "";
	}

	

}
