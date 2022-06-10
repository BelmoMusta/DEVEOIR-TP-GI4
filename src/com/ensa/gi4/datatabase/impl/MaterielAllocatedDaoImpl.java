package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDao;
import com.ensa.gi4.modele.MaterielAllocated;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielAllocatedDaoImpl extends GenericDAO<MaterielAllocated> implements MaterielAllocatedDao {

    @Override
    public List<MaterielAllocated> findAll() {
        return super.findAllMaterielAllocated("SELECT * FROM MaterielAllocated");
    }

    @Override
    public List<MaterielAllocated> findMaterielAllocatedByUserID(int userID) {
        return super.findMaterielAllocatedByUser("SELECT * FROM MaterielAllocated WHERE userID = ?", userID);
    }

    @Override
    public MaterielAllocated findMaterielAllocatedByUserIDAndMaterielID(int userID, int materielID)  {
        return super.findMaterielAllocatedByUserIDAndMaterielID("SELECT * FROM MaterielAllocated WHERE userID = ? AND materielID = ?;", userID, materielID);
    }

    @Override
    protected MaterielAllocatedRowMapper getRowMapper() {
        return new MaterielAllocatedRowMapper();
    }
}
