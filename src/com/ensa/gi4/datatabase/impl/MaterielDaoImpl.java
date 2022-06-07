package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Materiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
	@Autowired
	PersonneDAO personneDao;

	@Override
	public List<Materiel> findAll() {
		return super.findAll("SELECT * FROM MATERIEL;");
	}

	@Override
	public Materiel findOne(Long id) {
		return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
	}

	@Override
	protected MaterielRowMapper getRowMapper() { // template method design pattern
		return new MaterielRowMapper();
	}

	/*
	 * @Override public int quantiteMateriel(String code) { if(
	 * codeMatereielExiste(code)!= null) { return
	 * codeMatereielExiste(code).getQuantite(); } return 0; }
	 */
	@Override
	public int quantiteMateriel(String nom) {
		if (nomMatereielExiste(nom) != null) {
			String sql = "select count (*)  from materiel where name = '" + nom + "'";

			return super.count(sql);
		}
		return 0;
	}

	@Override
	public boolean estDisponible(String code) {
		if (nomMatereielExiste(code) != null) {
			return nomMatereielExiste(code).isDisponible();
		}
		return false;
	}

	@Override
	public Materiel nomMatereielExiste(String nom) {
		String sql = "select * from materiel where name = '" + nom + "' limit 1";
		return super.executeQuery(sql);
	}

	@Override
	public List<Materiel> listerMaterielsAlloue() {
		String sql = "select * from materiel where allouer=" + personneDao.getPersonneConnecte().getId() + "";
		return super.findAll(sql);
	}

	@Override
	public boolean ajouterMateriel(Materiel materiel) {
		String sql = "insert into materiel (name,code) values('" + materiel.getName() + "','" + materiel.getCode() + "')";
		if (super.insererOrUpdateOrDelete(sql) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean supprimmerMateriel(int id) {
		String sql = "delete from materiel where id=" +id+"";
		if (super.insererOrUpdateOrDelete(sql) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifierMateriel(int id, String nom, String code) {
		String sql = "update materiel set name ='"+nom+"',code='"+code+"' where id="+id+"";
		if (super.insererOrUpdateOrDelete(sql) != 0) {
			return true;
		}
		return false;
	}

	/*
	 * @Override public void diminuerQuantite(String code) { if(
	 * codeMatereielExiste(code)!= null) { int nouveauQuantite =
	 * quantiteMateriel(code)-1; String sql = "update materiel set quantite="+
	 * nouveauQuantite +""; super.insererOrUpdateOrDelete(sql);
	 * 
	 * } }
	 */

	/*
	 * @Override public void augmenterQuantite(String code) { if(
	 * codeMatereielExiste(code)!= null) { int nouveauQuantite =
	 * quantiteMateriel(code)+1; String sql = "update materiel set quantite="+
	 * nouveauQuantite +""; super.insererOrUpdateOrDelete(sql);
	 * 
	 * }
	 * 
	 * }
	 */

}
