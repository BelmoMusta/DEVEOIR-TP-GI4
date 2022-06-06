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
    public void addMateriel(Materiel materiel) {
    	 super.execute("INSERT INTO MATERIEL (NAME, CODE, QUANTITE, DISPONIBLE) VALUES ('"+materiel.getName()+"', '"+materiel.getCode()+"', "+materiel.getQuantite()+", TRUE);");
    }
    @Override
    public void deleteMateriel(Long id) {
    	super.execute("DELETE FROM MATERIEL WHERE id = "+id+"");
    }
}
