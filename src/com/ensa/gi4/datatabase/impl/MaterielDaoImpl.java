package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocations;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
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
        super.addMateriel("INSERT INTO MATERIEL(name,code,availability,_available) VALUES (?,?,?,?)",materiel);
    }

    @Override
    public void editMateriel(int id, Materiel materiel) {
        super.editMateriel("UPDATE MATERIEL SET name = ?, code = ?, availability = ? WHERE id = ? ", id, materiel);
    }

    @Override
    public void editAvailability(int id, Materiel materiel) {
        super.editAvailability("UPDATE MATERIEL SET availability = ? WHERE id = ? ",id,materiel);
    }

    @Override
    public void deleteMateriel(int id) {
        super.deleteMateriel("DELETE FROM MATERIEL WHERE id = ?",id);
    }

    @Override
    public void allocateMateriel(Materiel materiel, User user, int availability, int allocationQuantity) {
            super.allocateMateriel("UPDATE MATERIEL SET availability = ? WHERE id = ? ", "INSERT INTO allocations (userId, materielId, quantity) VALUES (?, ?, ?)", materiel, user, availability, allocationQuantity);

    }

    @Override
    public void returnMateriel(Materiel materiel, Allocations allocation, int availability) {
        super.returnMaterial("UPDATE MATERIEL SET availability = ? WHERE id = ?", "UPDATE allocations SET quantity = ? WHERE id = ?", materiel, allocation, availability);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
