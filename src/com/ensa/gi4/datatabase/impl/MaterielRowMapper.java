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

        String name = resultSet.getString(2);
        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        String code = resultSet.getString(3);
        int dispo = resultSet.getInt(4);
        int épuisé=resultSet.getInt(5);
        int alloué = resultSet.getInt(6);
        int user = resultSet.getInt(7);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setDisponible(dispo);
        materiel.setDisponible(épuisé);
        materiel.setAlloué(alloué);
        materiel.setAlloué(user);

        return materiel;
    }
}
