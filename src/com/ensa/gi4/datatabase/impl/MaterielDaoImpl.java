package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }
    
    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
    
    @Override
    public void addMateriel(Materiel materiel) {
    	super.execute("INSERT INTO MATERIEL (NAME, TYPE, ETAT) VALUES ('"+materiel.getName()+"', '"+materiel.getCode()+"', 'DISPONIBLE');");
    }
    @Override
    public void deleteMateriel(int id) {
    	super.execute("DELETE FROM MATERIEL WHERE ID = '"+id+"';");
    }

	@Override
	public void marquerMaterielIndisponible(int id) {
		super.execute("UPDATE MATERIEL SET ETAT = 'NON-DISPONIBLE' WHERE ID='"+id+"';");
	}

	@Override
	public boolean allouerMateriel(int idMateriel,  Date dateDebut, Date dateFin, int idUtilisateur) {
		Materiel m = super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", idMateriel);
		if(m.getEtat().equals("DISPONIBLE")) {
			super.execute("INSERT INTO ALLOCATION (IDMATERIEL, IDUSER, DATEDEBUT, DATEFIN) VALUES ('"+idMateriel+"', '"+idUtilisateur+"', '"+dateDebut+"', '"+dateFin+"');");
			super.execute("UPDATE MATERIEL SET ETAT = 'ALLOUE' WHERE ID='"+idMateriel+"';");
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void rendreMateriel(int id) {
		super.execute("UPDATE MATERIEL SET ETAT = 'DISPONIBLE' WHERE ID='"+id+"';");
	}

	@Override
	public void modifierMateriel(int id, String nom, String type) {
		super.execute("UPDATE MATERIEL SET NAME = '"+nom+"', TYPE = '"+type+"' WHERE ID='"+id+"';");
	}

}
