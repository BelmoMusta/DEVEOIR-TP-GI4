package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocationDao;
import com.ensa.gi4.modele.Allocation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AllocationDaoImpl extends GenericDAO<Allocation> implements AllocationDao {
    @Override
    public List<Allocation> findAll() {
        return  super.findAll("SELECT * FROM GESTIONALLOCATION;");
    }
    public List<Allocation> findAllPerUser(Long idUser) {
        return  super.findAll("SELECT * FROM GESTIONALLOCATION WHERE IDUSER="+idUser+";");
    }

    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return new allocationRowMapper();
    }
}
