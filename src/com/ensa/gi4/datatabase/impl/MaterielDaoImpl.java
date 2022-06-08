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
        return super.findOne("SELECT * FROM MATERIEL WHERE ID_MATERIEL=?;", id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
   
    @Override
    public void addMateriel(Materiel materiel) {
    	 super.execute("INSERT INTO MATERIEL (NAME, CODE, DISPONIBLE) VALUES ('"+materiel.getName()+"', '"+materiel.getCode()+"', TRUE);");
    }
    @Override
    public void deleteMateriel(Long id) {
    	super.execute("DELETE FROM MATERIEL WHERE ID_MATERIEL = "+id);
    }

	@Override
	public void marquerMaterielIndisponible(Long id) {
		super.execute("UPDATE MATERIEL SET DISPONIBLE = FALSE WHERE ID_MATERIEL="+id);	
	}

	@Override
	public void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur, String usernameUtilisateur) {
		super.execute("UPDATE MATERIEL SET ALLOUE = TRUE , DUREE='"+dure+"' , UTILISATEUR_ID = "+idUtilisateur+" , UTILISATEUR_USERNAME = '"+usernameUtilisateur+"' WHERE ID_MATERIEL="+idMateriel);
	}
	@Override
	public void rendreMateriel(Long idMateriel) {
		super.execute("UPDATE MATERIEL SET ALLOUE = FALSE  , UTILISATEUR_ID = NULL, DUREE = NULL, UTILISATEUR_USERNAME = NULL WHERE ID_MATERIEL="+idMateriel);
		
	}

	@Override
	public List<Materiel> listeMaterielAlloue(Long id) {
		return super.findAll("SELECT * FROM MATERIEL WHERE UTILISATEUR_ID="+id+";");
	}

	@Override
	public List<Materiel> listeMaterielAlloueAll() {
		return super.findAll("SELECT * FROM MATERIEL WHERE ALLOUE = TRUE;");
	}

	@Override
	public void modifierMateriel(Long id, String code) {
		super.execute("UPDATE MATERIEL SET CODE = '"+code+"' WHERE ID_MATERIEL="+id);
	}
}
