package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MaterielAllocatedDaoImpl extends GenericDAO<MaterielAllocated> implements MaterielAllocatedDao {
    private UserDaoImp userDaoImp = new UserDaoImp();
    @Override
    public List<MaterielAllocated> findAll() {
        return super.findAll("SELECT * FROM MATERIELALLOCATED;");
    }

    @Override
    public List<MaterielAllocated> listerMesMateriels(int id) {
        return super.findAllById("SELECT * FROM MATERIELALLOCATED WHERE idUser=?;",id);
    }

    @Override
    protected RowMapper<MaterielAllocated> getRowMapper() {
        return  new MaterielAllocatedRowMapper();
    }
}
