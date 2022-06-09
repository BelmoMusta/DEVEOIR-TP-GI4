package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String type = resultSet.getString(3);
        String etat = resultSet.getString(4);
        
        materiel.setId(id);
        materiel.setName(name);
        materiel.setCode(type);
        materiel.setEtat(etat);

        return materiel;
    }
}
