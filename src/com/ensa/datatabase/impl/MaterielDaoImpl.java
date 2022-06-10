package com.ensa.datatabase.impl;

import com.ensa.datatabase.api.MaterielDao;
import com.ensa.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;
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
    protected RowMapper<Materiel> getRowMapper() {
        return new MaterielRowMapper();
    }
}
