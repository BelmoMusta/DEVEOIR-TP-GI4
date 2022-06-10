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
    public Materiel findOne(Integer id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public void delete(Integer id) {
        super.delete("DELETE FROM materiel WHERE id=?;", id);
    }

    @Override
    public void allocate(Materiel materiel) {
        int current = materiel.getAllocated()+1;
        this.jdbcTemplate.update("UPDATE materiel SET allocated=? WHERE id=?;", current, materiel.getId());
    }

    @Override
    public void deallocate(Materiel materiel) {
        int current = materiel.getAllocated()-1;
        this.jdbcTemplate.update("UPDATE materiel SET allocated=? WHERE id=?;", current, materiel.getId());
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

}
