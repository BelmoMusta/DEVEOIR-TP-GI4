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
        String code = resultSet.getString(3);
        int dispo = resultSet.getInt(4);
        int alloué = resultSet.getInt(5);
        int employee = resultSet.getInt(6);
        materiel.setCode(code);
        materiel.setName(name);
       materiel.setDisponible(dispo);
       materiel.setAlloué(alloué);
        materiel.setEmployee(employee);

        return materiel;
    }
}
