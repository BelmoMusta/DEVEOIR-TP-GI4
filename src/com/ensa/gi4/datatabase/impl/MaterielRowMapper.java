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
        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        String code = resultSet.getString(3);
        String type = resultSet.getString(4);
        Boolean isAvailable = resultSet.getBoolean(5);
        Boolean isAllocated = resultSet.getBoolean(6);
        int quantite = resultSet.getInt(7);

        materiel.setId(id);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setType(type);
        materiel.setAvailable(isAvailable);
        materiel.setAllocated(isAllocated);
        materiel.setQuantite(quantite);


        return materiel;
    }
}
