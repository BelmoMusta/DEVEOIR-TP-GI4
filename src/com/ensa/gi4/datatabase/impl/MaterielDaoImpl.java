package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Autowired
	UserDao userDao;
	
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

	@Override
	public List<Materiel> listeAllocation(String name) {
		
		String query = "SELECT * FROM materiel WHERE allouer=" +userDao.getUser(name).getId()+ "";
	 return super.findAll(query);
	}

	@Override
	public boolean ajouterMateriel(Materiel materiel) {
		

		String query = "insert into materiel (name,code) values('" + materiel.getName() + "','" + materiel.getCode() + "')";
		if(super.executeWithVerif(query)!=0) {
			return true;
		} 
		else { return false;
		
		}
		
		
		
	}

	@Override
	public boolean supprimmerMateriel(int id) {
		String query = "DELETE FROM materiel WHERE id="+id+"";
		if (super.executeWithVerif(query) != 0) {
			return true;
		}
		return false;
	
	}
	
	

	@Override
	public boolean modifierMateriel(int id, String nom, String code) {
		String sql = "update materiel set name ='"+nom+"',code='"+code+"' where id="+id+"";
		if (super.executeWithVerif(sql) != 0) {
			return true;
		}
		return false;
	}

	

	
	
    
    
}
