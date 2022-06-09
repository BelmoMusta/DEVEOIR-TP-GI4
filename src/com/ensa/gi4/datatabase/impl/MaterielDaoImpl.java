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
        return super.findAll("SELECT * FROM materiel;");
    }

    @Override
    public Materiel findOne(Long id) {
        return super.findOne("SELECT * FROM materiel WHERE ID=?;", id);
    }
    


	@Override
	public void ajouterNvMateriel(Materiel 	m) {
		String sql = "INSERT INTO materiel (name, code) VALUES ('"+m.getName()+"', '"+m.getCode()+"') ";
		super.inseretUpdateDelete(sql);
	
	}

	@Override
	public Materiel matereielExiste(String nom) {
		String sql = "select * from materiel where name = '" + nom + "' limit 1";
		return super.executeQuery(sql);
	}
	@Override
	public void supprimerMateriel(Long id) {
		String sql = "delete from materiel where id = '" + id + "' limit 1";
		super.inseretUpdateDelete(sql);
		
	}

	@Override
	 public boolean modifierInfosMateriel(Long id, String nom, String code) {
		String sql = "update materiel set name ='"+nom+"',code='"+code+"' where id="+id+"";
		if (super.inseretUpdateDelete(sql) != 0) {
			return true;
		}else {
			return false;
		}
		
	
		
	}

	@Override
	public boolean indisponibleMateriel(Long id) {
		String sql = "update materiel set disponible ='false' where id="+id+"";
		if (super.inseretUpdateDelete(sql) != 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public int quantiteMateriel(String name) {
		if (matereielExiste(name) != null) {
			String sql = "select count (*)  from materiel where name = '" + name+ "'";

			return super.count(sql);
		}
		return 0;
	}

	@Override
	public void afficherMaterielAlloueParUtilisateur() {

		List<Materiel> listMateriel = super.findAll("select * from materiel where allouer is not null");
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
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

	@Override
	public List<Materiel> listerMaterielsAlloue() {
		String sql = "select * from materiel where allouer=" + personneDao.getPersonneConnecte().getId() + "";
		return super.findAll(sql);
	}



}
