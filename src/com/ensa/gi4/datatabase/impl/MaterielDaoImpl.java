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
    public Materiel findOne(String id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE code=?;", id);
    }
    
    @Override
    public Materiel findOneById(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE idM=?;", String.valueOf(id));
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

    @Override
    public int addOne(Materiel materiel) {
        return super.addOne("INSERT INTO materiel (name, code, qte) VALUES (?,?,?);", materiel.getName(), materiel.getCode(), materiel.getQte());
    }
    
    @Override
    public void removeOne(String code) {
       super.removeOne("DELETE FROM MATERIEL WHERE code=?;", code);
    }

    @Override
    public void updateOne(Materiel materiel) {
        super.updateOne("UPDATE materiel SET name=?, code=?, qte=?, disponible=? WHERE idM=?;", materiel.getName(),materiel.getCode(),materiel.getQte(), materiel.getDisponible(), materiel.getIdM());
    }
}
