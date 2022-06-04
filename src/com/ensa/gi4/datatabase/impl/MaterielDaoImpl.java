package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Autowired
    private I18nService i18nService;
    @Autowired
    private EntityUtils entityUtils;

    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id.toString());
    }

    @Override
    public boolean isAllocated(Long id) {
        Materiel materiel = this.findOne(id);
        if(materiel != null)
            return materiel.isAllocated();
        else
            return false;
    }

    @Override
    public void allocate(Long id) {
        Materiel materiel = this.findOne(id);
        if(materiel != null)
            materiel.setAllocated(true);
    }

    @Override
    public void delete(Long id) {
        super.delete("DELETE FROM materiel WHERE id=?", id.toString());
    }



    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper(entityUtils);
    }
}
