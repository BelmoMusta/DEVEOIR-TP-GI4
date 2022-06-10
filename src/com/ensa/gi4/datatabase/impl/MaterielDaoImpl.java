package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
	
	
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }
 
    @Override
    public Materiel findOneMateriel(String code) {
        return super.findOneMateriel("SELECT * FROM MATERIEL WHERE code=?;", code);
    }

    @Override 
        protected RowMapper<Materiel> getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

   	@Override
   	public void AjouterMaterial(Materiel m) {
   		super.AddMaterial("INSERT INTO MATERIEL(code, name,dispo) VALUES(?,?,?)", m);
   	}

	@Override
	public void SupprimerMateriel(String code) {
		super.RemoveMateriel("delete from materiel where code = ?", code);
	}

	@Override
	public void ModifierMateriel(String Lcode,String Ncode) {
		super.UpdateMateriel("UPDATE materiel SET code = ? WHERE code = ?", Ncode,Lcode);
		
	}
	
	@Override
	public void allouerMateriel(Materiel m,User user) {
		
		super.allouerMateriel("INSERT INTO allocation(code, username) VALUES(?,?)", m.getCode(),user.getUsername());
		
	}
	
	@Override
	public void supprimerMaterielAlloue(Materiel materiel) {
	
		super.supprimerMaterielAlloue("DELETE from allocation where code=?", materiel.getCode());
		
	}

	@Override
	public void ModifierDisponibilite(Materiel meteriel) {
		super.ModifierDisponibilite("UPDATE materiel SET dispo = ? WHERE code = ?",meteriel.getDispo(),meteriel.getCode());
		
	}

	@Override
	public Materiel RechercheMateriel(String code) {
		return super.findOneMateriel("SELECT * FROM MATERIEL WHERE code=?;", code);
	}


	
	 @Override 
     protected RowMapper<Allocation> getRowMapperAllocation() { // template method design pattern
     return new AlloactionRowMapper();
 }

	@Override
	public List<Allocation> findListAlloueByName(String username) {
		
		return super.findListAlloueByName("SELECT * FROM ALLOCATION WHERE username = ?;", username);
	}

	@Override
	public List<Allocation> findAllListAlloue() {
		return super.findAllListAlloue("SELECT * FROM allocation;");
		
	}

	@Override
	public Allocation findMaterielAlloue(Materiel materiel)  {

		return super.findMaterielAlloue("SELECT * FROM ALLOCATION WHERE code=? ;",materiel.getCode());

	}	
}

	


