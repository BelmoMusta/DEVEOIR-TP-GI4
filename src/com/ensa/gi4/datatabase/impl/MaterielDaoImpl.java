package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAllMateriel() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOneMateriel(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
    
    @Override
     public Materiel findWithCode(String code){
    	
    	return super.executeQuery("SELECT * FROM materiel WHERE code='"+code+"'");
    }
    
    @Override
    public Boolean isDispo(String code) {
           return findWithCode(code).getDispo();
        
    }

	

	@Override
	public void nonDispo(String code) {
	   Boolean disponible =false;
		super.UpdateQuery("UPDATE materiel SET dispo='"+disponible+"'");
		
	}

	

	
	
    
    
}
