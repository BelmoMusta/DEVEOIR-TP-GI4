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
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        String allocated = resultSet.getString("allocated");
        materiel.setId(id);
        materiel.setType(type);
        materiel.setName(name);
        if("true".equalsIgnoreCase(resultSet.getString("allocated"))){
            materiel.setAllocated(true);
        }else
            materiel.setAllocated(false);
        return materiel;
    }
}
