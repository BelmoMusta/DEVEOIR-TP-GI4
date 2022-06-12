package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocationsDao;
import com.ensa.gi4.modele.Allocations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllocationsDaoImpl extends GenericDAO<Allocations> implements AllocationsDao {

    @Override
    public List<Allocations> findAll() {
        return super.findAllMaterielAllocated("SELECT * FROM allocations");
    }

    @Override
    public List<Allocations> findMaterielAllocatedByUserID(int userId) {
        return super.findMaterielAllocatedByUser("SELECT * FROM allocations WHERE userId = ?", userId);
    }

    @Override
    public Allocations findMaterielAllocatedByMaterielID(int materielId) {
        return super.findMaterielAllocatedByMaterielID("SELECT * FROM allocations WHERE materielId = ?", materielId);
    }

    @Override
    public Allocations findMaterielAllocatedByUserIDAndMaterielID(int userId, int materielId) {
        return super.findMaterielAllocatedByUserIDAndMaterielID("SELECT * FROM allocations WHERE userId = ? AND materielId = ?;", userId, materielId);
    }

    @Override
    protected RowMapper<Allocations> getRowMapper() {
        return new AllocationsRowMapper();
    }
}
