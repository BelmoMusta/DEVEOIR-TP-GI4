package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
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



	/*@Override
	public int quantiteMateriel(String code) {
		if( codeMatereielExiste(code)!= null) {
			return codeMatereielExiste(code).getQuantite();
		}
		return 0;
	}*/
	@Override
	public int quantiteMateriel(String nom) {
		if( nomMatereielExiste(nom)!= null) {
			String sql = "select count (*)  from materiel where name = '"+nom+"'";
			
			return super.count(sql);
		}
		return 0;
	}

	@Override
	public boolean estDisponible(String code) {
		if( nomMatereielExiste(code)!= null) {
			return nomMatereielExiste(code).isDisponible();
		}
		return false;
	}

	@Override
	public Materiel nomMatereielExiste(String nom) {
		String sql = "select * from materiel where name = '"+nom+"' limit 1";
		return super.executeQuery(sql);
	}

	/*@Override
	public void diminuerQuantite(String code) {
		if( codeMatereielExiste(code)!= null) {
			int nouveauQuantite = quantiteMateriel(code)-1;
			String sql = "update materiel set quantite="+ nouveauQuantite +"";
			super.insererOrUpdateOrDelete(sql);
			
		}	
	}*/

/*	@Override
	public void augmenterQuantite(String code) {
		if( codeMatereielExiste(code)!= null) {
			int nouveauQuantite = quantiteMateriel(code)+1;
			String sql = "update materiel set quantite="+ nouveauQuantite +"";
			super.insererOrUpdateOrDelete(sql);
			
		}
		
	}*/

	
	
	
}
