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
    public Materiel findMateriel(Long id) {
        try{
            return super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", id);
        }
        catch (Exception e){
            System.out.println("Materiel Not found");
        }

        return null;
    }

    @Override
    public String addMateriel(Materiel materiel) {
        super.addOne("INSERT INTO MATERIEL VALUES(?,?,?,?,?);",materiel.getIdMateriel(),materiel.getMaterielName(),materiel.getMaterielCode(),materiel.getMaterielType(),materiel.isDisponible());
        return "Materiel ajouté";
    }

    @Override
    public String supprimerMateriel(int id) {
        super.deleteOne("DELETE FROM MATERIEL WHERE idMateriel=?;", id);
        return "Materiel supprimé";
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
