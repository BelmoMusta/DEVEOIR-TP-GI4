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
    @Override
	 public void allouer(long idUser,String name) {
    String alloue="nonAllouer";
    	long nbr=super.countElementsNotAlloue("SELECT COUNT(*) FROM MATERIEL WHERE name=? and alloue=?;", name,alloue);
    	if(nbr>0) {
    		Long all=idUser;
    		String allouer1=all.toString();
    	int a=super.allouer("UPDATE MATERIEL SET alloue=? WHERE name=? and alloue='nonAllouer' LIMIT 1;", allouer1,name);
    		System.out.println("bien alouee"+a);
    	}
    	else  System.out.println("ce matériel non disponible ou épuisé");

    	
    	
    }
}
