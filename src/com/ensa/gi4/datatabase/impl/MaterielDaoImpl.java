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
           if(findWithCode(code)!=null ) {
        	   return true;
           }
           else {
        	   return false;
           }
    }

	@Override
	public int quantiteMateriel(String code) {
		
		if(findWithCode(code)!=null)
		{
			 return findWithCode(code).getQuantite();
			
		}
		
		else {
			return 0;
		}
	}

	@Override
	public void diminuerQuantite(String code) {
		int newQuantite = (quantiteMateriel(code))-1;
		super.UpdateQuery("UPDATE materiel SET quantite='"+newQuantite+"'");
		
	}
	
	
    
    
}
