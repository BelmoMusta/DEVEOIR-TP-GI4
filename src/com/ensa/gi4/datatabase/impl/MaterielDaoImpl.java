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

	@Override
	public int quantiteMateriel(String nom) {
		if (nomMatereielExiste(nom) != null) {
			String sql = "select count (*)  from materiel where name = '" + nom + "'";

			return super.count(sql);
		}
		return 0;
	}

	@Override
	public boolean estDisponible(int id) {
		String sql = "select * from materiel where id = " + id + "";
		if (super.executeQuery(sql) != null) {
			return super.executeQuery(sql).isDisponible();
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
	public void ajouterMateriel(Materiel materiel) {
		String sql = "insert into materiel (name,code) values('" + materiel.getName() + "','" + materiel.getCode()
				+ "')";
		super.insererOrUpdateOrDelete(sql);

	}

	@Override
	public void supprimmerMateriel(int id) {
		String sql = "delete from materiel where id=" + id + "";
		super.insererOrUpdateOrDelete(sql);
	}

	@Override
	public void modifierMateriel(int id, String code) {
		String sql = "update materiel set code='" + code + "' where id=" + id + "";
		super.insererOrUpdateOrDelete(sql);

	}

	@Override
	public void marquerMaterielIndisponible(int id) {
		String sql = "update materiel set disponible = false where id =" + id + "";
		super.insererOrUpdateOrDelete(sql);

	}

	@Override
	public void afficherMaterielAlloueParUtilisateur() {

		List<Materiel> listMateriel = super.findAll("select * from materiel where allouer is not null");// le materiel
																										// alloué
		if (!listMateriel.isEmpty()) {
			for (int i = 0; i < listMateriel.size(); i++) {
				System.out.println("Name = " + listMateriel.get(i).getName() + " ;code =  "
						+ listMateriel.get(i).getCode() + " ;nom utilisateur : " + super.extraireString(
								"select name from users where id =" + listMateriel.get(i).getAllouer() + ""));
			}
		} else {
			System.out.println("pas de matériel alloués");
		}

	}

	@Override
	public boolean allouerMateriel(String nom, String duree) {
		if (personneDao.getPersonneConnecte() != null) {
			String sql = "update materiel set allouer= " + personneDao.getPersonneConnecte().getId() + ", duree = '"
					+ duree + "' where allouer IS NULL and disponible = true and name='" + nom + "' limit 1";

			if (super.insererOrUpdateOrDelete(sql) != 0) {
				return true;
			} else {
				return false;
			}

		}
		return false;

	}

	@Override
	public boolean rendreMateriel(int id) {
		String sql = "update materiel set allouer= null, duree = null where allouer="
				+ personneDao.getPersonneConnecte().getId() + " and id=" + id + "";
		if (super.insererOrUpdateOrDelete(sql) != 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean idMaterielExiste(int id) {
		String sql = "select * from materiel where id = " + id + "";
		if (super.executeQuery(sql) != null) {
			return true;
		}
		return false;
	}

}
