package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Allocation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> getAllMateriels() {
        return super.getAllMateriels("SELECT * FROM Materiel;");
    }

    @Override
    public Materiel getMaterielByName(String name) {
        return super.getMaterielByName("SELECT * FROM Materiel WHERE name = ?;", name);
    }

    @Override
    public void addMateriel(Materiel materiel) {
        super.addMateriel("INSERT INTO Materiel(name,code,availability,quantity) VALUES (?,?,?,?)",materiel);
    }

    @Override
    public void deleteMateriel(String nom) {
        super.deleteMateriel("DELETE FROM Materiel WHERE name = ?",nom);
    }

    @Override
    public void editMaterielName(String oldName, String newName) {
        super.editMaterielName("UPDATE Materiel SET name = ? WHERE name = ?;", oldName, newName);
    }

    @Override
    public void editMaterielCode(String name, String code) {
        super.editMaterielCode("UPDATE Materiel SET code = ? WHERE name = ?;", name, code);
    }

    @Override
    public void editMaterielAvailability(String name, boolean availability) {
        super.editMaterielAvailability("UPDATE Materiel SET availability = ? WHERE name = ?;", name, availability);
    }

    @Override
    public void editMaterielQuantity(String name, int quantity) {
        super.editMaterielQuantity("UPDATE Materiel SET quantity = ? WHERE name = ?;", name, quantity);
    }

    @Override
    public void allocateMateriel(Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity) {
        if(!ifExist)
            super.allocateMateriel("UPDATE Materiel SET quantity = ? WHERE id = ? ", "INSERT INTO MaterielAllocated (userID, materielID, quantity) VALUES (?, ?, ?)", materiel, userID, quantity, ifExist, materielAllocatedQuantity);
        else
            super.allocateMateriel("UPDATE Materiel SET quantity = ? WHERE id = ? ", "UPDATE MaterielAllocated SET quantity = ? WHERE userID = ? AND materielID = ?", materiel, userID, quantity, ifExist, materielAllocatedQuantity);
    }

    @Override
    public void returnMateriel(Materiel materiel, Allocation allocation, int quantity, int userID) {
        if  (allocation.getQuantity() - quantity == 0)
            super.returnMaterial("UPDATE Materiel SET quantity = ? WHERE id = ?", "DELETE FROM MaterielAllocated WHERE userID = ? AND materielID = ?", materiel, allocation, quantity, userID);
        else
            super.returnMaterial("UPDATE Materiel SET quantity = ? WHERE id = ?", "UPDATE MaterielAllocated SET quantity = ? WHERE userID = ? AND materielID = ?", materiel, allocation, quantity, userID);
    }

    @Override
    protected MaterielRowMapper getRowMapper() {
        return new MaterielRowMapper();
    }
}
