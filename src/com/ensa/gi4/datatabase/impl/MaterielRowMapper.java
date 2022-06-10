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

        materiel.setId(resultSet.getInt("ID"));
        materiel.setUser_id(resultSet.getString("USER_ID"));
        materiel.setCode( resultSet.getString("CODE"));
        materiel.setName( resultSet.getString("NAME"));
        materiel.setAlloue( resultSet.getBoolean("ALLOUE"));
        materiel.setType(resultSet.getString("TYPE"));
        materiel.setDuree(resultSet.getString("DUREE"));
        materiel.setUsername(resultSet.getString("USERNAME"));
        materiel.setDisponible(resultSet.getBoolean("disponible"));


        return materiel;
    }
}
