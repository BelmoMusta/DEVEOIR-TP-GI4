package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
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
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }


}
