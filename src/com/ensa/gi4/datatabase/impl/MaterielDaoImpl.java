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
    public Materiel findOne(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM material WHERE id=?";
        this.jdbcTemplate.update(query, id);
    }

    @Override
    public void edit(int id, String newName) {
        String query = "UPDATE material SET name=? WHERE id=?";
        this.jdbcTemplate.update(query,newName,id);
    }

    @Override
    public Boolean isAllocated(int id) {
        Materiel materiel = this.findOne(id);
        if(materiel!=null){
            return materiel.getAllocated();
        }
        else return false ;
    }

    @Override
    public void toAllocate(int id) {
        String sql = "UPDATE material SET allocated=? WHERE id=?";
        this.jdbcTemplate.update(sql,"true",id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
