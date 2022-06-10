package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDao;
import com.ensa.gi4.modele.Allocation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielAllocatedDaoImpl extends GenericDAO<Allocation> implements MaterielAllocatedDao {

    @Override
    public List<Allocation> getAllAllocations() {
        return super.getAllAllocations("SELECT Users.username, Materiel.name, MaterielAllocated.quantity FROM MaterielAllocated" +
                " INNER JOIN Users ON MaterielAllocated.userID = Users.id" +
                " INNER JOIN Materiel ON MaterielAllocated.materielID = Materiel.id");
    }

    @Override
    public List<Allocation> getAllocationsByUser(int userID) {
        return super.getAllocationsByUser("SELECT Users.username, Materiel.name, MaterielAllocated.quantity FROM MaterielAllocated" +
                " INNER JOIN Users ON MaterielAllocated.userID = Users.id" +
                " INNER JOIN Materiel ON MaterielAllocated.materielID = Materiel.id" +
                " WHERE userID = ?", userID);
    }

    @Override
    public Allocation ifMaterielIsAllocatedByUser(int userID, int materielID)  {
        return super.ifMaterielIsAllocatedByUser("SELECT Users.username, Materiel.name, MaterielAllocated.quantity FROM MaterielAllocated" +
                " INNER JOIN Users ON MaterielAllocated.userID = Users.id" +
                " INNER JOIN Materiel ON MaterielAllocated.materielID = Materiel.id" +
                " WHERE userID = ? AND materielID = ?;", userID, materielID);
    }

    @Override
    public Allocation getAllocationByMateriel(int materielID) {
        return super.getAllocationByMateriel("SELECT * FROM MaterielAllocated WHERE materielID = ? LIMIT 1;", materielID);
    }

    @Override
    protected MaterielAllocatedRowMapper getRowMapper() {
        return new MaterielAllocatedRowMapper();
    }
}
