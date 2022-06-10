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

        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String code = resultSet.getString(3);
        boolean availability = resultSet.getBoolean(4);
        int quantity = resultSet.getInt(5);
        materiel.setName(name);
        materiel.setCode(code);
        materiel.setAvailability(availability);
        materiel.setQuantity(quantity);
        materiel.setId(id);


        return materiel;
    }
}
