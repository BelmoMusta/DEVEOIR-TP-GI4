package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
	
	@Override
	public List<Materiel> findByName(String name) {
		    return super.findAll("SELECT * from MATERIEL WHERE name Like ?;",new Object[] {"%"+name+"%"}); 
	}
	
	@Override
	public boolean checkdispo(String type) {	
		return (!super.findAll("SELECT *from MATERIEL WHERE type=? AND alloue=? ;",new Object[]{type,false}).isEmpty());
		
	}
	@Override
	public int allouer(int userId,String name) {
	      return super.update("UPDATE MATERIEL SET alloue=?,userId=? WHERE name=? AND alloue=false;",     
	              new Object[] { true, userId,name});
	}
	
	@Override
	public int rendreMateriel(int userId,String name) {
		if((super.findOne("select * FROM MATERIEL WHERE name=? AND userid=? ",     
            new Object[] {name,userId}))!=null) {
		    return super.update("UPDATE MATERIEL SET alloue=?,userId=? WHERE name=?",     
	              new Object[] { false, null,name});   
		}
		else {
			return 0;
		}
	}
	
	@Override
	public List<Materiel> findAlloueByMe(int id) {
		return super.findAll("SELECT * from MATERIEL WHERE userid=? AND alloue=? ;",new Object[] {id,true}); 

	}
	
	@Override
	 public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;",new Object[] {});
    }
    
    @Override
    public int insert(String name,String type) {
        return super.update("INSERT INTO MATERIEL (name, type) VALUES(?,?);",
        		new Object[] {name, type});
        }
    	
    
    @Override 
    public boolean isExistName(String name) {
    	return !super.findAll("select * FROM MATERIEL WHERE name=? ",     
                new Object[] {name}).isEmpty();
    }

    @Override
    public int update(String newName,String type,String name) {
      return super.update("UPDATE MATERIEL SET name=?, type=? WHERE name=?",
          new Object[] { newName,type,name });
     
    }
     
    @Override
	public int deleteByName(String name) {
		return super.update("DELETE FROM MATERIEL WHERE name=?", new Object[] {name});

	}
    
    @Override
	public int MarqueIndisponible(String name) {
    	return super.update("UPDATE MATERIEL SET alloue=? WHERE name=?",
    	          new Object[] {true,name });
    	
		
	}
    
	@Override
	public List<Materiel> findAlloueByUser(String name) {
	    return super.findAll("SELECT * from MATERIEL INNER JOIN USER on user.userid=materiel.userid WHERE username=?"
	    		,new Object[] {name});
	}
	
	
    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

	


	
}
	

	
	

	



