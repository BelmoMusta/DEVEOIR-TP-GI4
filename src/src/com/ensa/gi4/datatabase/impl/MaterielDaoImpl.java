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
    public Materiel findone(Long id) {
        return super.findone("SELECT * FROM MATERIEL WHERE id_materiel=?;", id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
    

	@Override
	public void create(Materiel t) {
		String sql="INSERT INTO materiel(name, code) VALUES(?,?) ";
		super.createExe(sql,t.getName(),t.getCode());
		
	}

	@Override
	public void update(String Name,String Code, Long id) {
		String sql="UPDATE materiel set name=?,code=? where id_materiel=? ; ";
		super.updateExe(sql,Name,Code,id);
		
	}

	@Override
	public void delete(Long id) {
		
		String sql="delete from materiel where id_materiel=? ";
		super.EXE(sql, id);

	}

	

	@Override
	public List<Materiel> listeMaterielAlloue(Long id) {
	 String sql="SELECT * FROM MATERIEL WHERE user_id=?";
	 
	
		return super.MaterelAlloueExe("SELECT * FROM MATERIEL WHERE user_id=?",id);
	}

	@Override
	public List<Materiel> listeMaterielAlloueAll() {
		 String sql="SELECT * FROM MATERIEL ";
		return super.findAll(sql);
	}

@Override
public void allouerMateriel(Long id_M, String dure, Long id_user, String user_name) {
	
	String sql = "UPDATE MATERIEL SET alloue = TRUE , DUREE=? , user_id = ? , user_username = ? WHERE id_materiel=?";
	 super.allouerExe(sql,dure,id_user,user_name,id_M);
}

@Override
public void rendreMateriel(Long id_M) {
	String sql ="UPDATE MATERIEL SET ALLOUE = FALSE  , user_id = NULL, DUREE = NULL, user_username = NULL WHERE ID_MATERIEL=?";
	super.EXE(sql,id_M);
}

@Override
public void rendre_Materiel_indisponible(Long id_M) {
	String sql="UPDATE MATERIEL SET DISPONIBLE = FALSE WHERE ID_MATERIEL=?";
	super.EXE(sql,id_M);
	
}
	

	
}
