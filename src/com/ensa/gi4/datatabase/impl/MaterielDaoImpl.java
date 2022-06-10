package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
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
    public void addMateriel(Materiel materiel) {
        super.addMateriel("INSERT INTO MATERIEL(name,code,availability,quantity) VALUES (?,?,?,?)",materiel);
    }

    @Override
    public void editMateriel(int id, Materiel materiel) {
        super.editMateriel("UPDATE MATERIEL SET name = ?, code = ?, quantity = ? WHERE id = ? ", id, materiel);
    }

    @Override
    public void editMaterielAvailability(int id, Materiel materiel) {
        super.editMaterielDisponibility("UPDATE MATERIEL SET availability = ? WHERE id = ? ",id,materiel);
    }

    @Override
    public void deleteMateriel(int id) {
        super.deleteMateriel("DELETE FROM MATERIEL WHERE id = ?",id);
    }

    @Override
    public void allocateMateriel(Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity) {
        if(!ifExist)
            super.allocateMateriel("UPDATE MATERIEL SET quantity = ? WHERE id = ? ", "INSERT INTO materielAllocated (userID, materielID, quantity) VALUES (?, ?, ?)", materiel, userID, quantity, ifExist, materielAllocatedQuantity);
        else
            super.allocateMateriel("UPDATE MATERIEL SET quantity = ? WHERE id = ? ", "UPDATE materielAllocated SET quantity = ? WHERE userID = ? AND materielID = ?", materiel, userID, quantity, ifExist, materielAllocatedQuantity);
    }

    @Override
    public void returnMateriel(Materiel materiel, MaterielAllocated materielAllocated, int quantity) {
        super.returnMaterial("UPDATE MATERIEL SET quantity = ? WHERE id = ?", "UPDATE MATERIELALLOCATED SET quantity = ? WHERE id = ?", materiel, materielAllocated, quantity);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
