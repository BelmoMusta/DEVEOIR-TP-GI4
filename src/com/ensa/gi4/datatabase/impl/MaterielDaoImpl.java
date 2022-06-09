package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Override
    public List<Materiel> findAll() {

        String query = "SELECT * FROM MATERIEL;";
        return super.findAll(query);

    }

    @Override
    public Materiel findOne(Long id) {

        String query = "SELECT * FROM MATERIEL WHERE ID=?";
        return super.findOne(query, id);

    }

    @Override
    public String deleteMateriel(Long id) {

        // deletion code here
        String query = "DELETE FROM MATERIEL WHERE ID=?";
        super.deleteOne(query, id);
        return "Le materiel a été supprimé avec succes";

    }

    @Override
    public String addMateriel(Materiel materiel) {

        // addition code here
        String query = "INSERT INTO MATERIEL(name, code, type, stock, dispo) VALUES(?,?,?,?,?)";
        super.addOne(query, materiel.getName(), materiel.getCode(), materiel.getType(), materiel.getStock(), materiel.getDispo());
        return "Le materiel a été ajouté avec succes";

    }

    @Override
    public String nonDispoMateriel(Long id) {

        String query = "UPDATE MATERIEL SET STOCK = 0, DISPO = false WHERE ID = ?";
        super.noDipoMateriel(query, id);
        return "Le materiel n'est pas diponible dans votre stock maintenant veuillez vérifier";

    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern

        return new MaterielRowMapper();
    }
}
