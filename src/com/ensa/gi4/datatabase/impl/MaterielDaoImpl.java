package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Autowired
    private EntityUtils entityUtils;

    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public List<Materiel> findAllWithCriteria(List<String> queryConditions, List<String> queryValues){
        String query = "SELECT * FROM MATERIEL WHERE " + String.join(" and ", queryConditions);
        return this.jdbcTemplate.query(query, getRowMapper(), queryValues.toArray());
    }

    @Override
    public Materiel findOne(Integer id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id.toString());
    }

    @Override
    public boolean isAvailable(Integer id) {
        Materiel materiel = this.findOne(id);
        if(materiel != null)
            return materiel.getAvailable()>0;
        else
            return false;
    }

    @Override
    public void allocate(Integer id) {
        int newAvailable = this.findOne(id).getAvailable()-1;
        this.jdbcTemplate.update("UPDATE materiel SET available=? WHERE id=?", newAvailable, id);
    }

    @Override
    public void deallocate(Integer id) {
        int newAvailable = this.findOne(id).getAvailable()+1;
        this.jdbcTemplate.update("UPDATE materiel SET available=? WHERE id=?",newAvailable, id);
    }

    @Override
    public void delete(Integer id) {
        super.delete("DELETE FROM materiel WHERE id=?", id.toString());
    }

    @Override
    public void update(int id, String[] fields, String[] values) {
        String updates = String.join(",", Arrays.stream(fields).map(s -> s+"=?").toArray(String[]::new));
        String sql = "UPDATE materiel SET " + updates + "WHERE id=" + id; //id est un "safe arg"
        this.jdbcTemplate.update(sql, values);
    }


    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper(entityUtils);
    }
}
