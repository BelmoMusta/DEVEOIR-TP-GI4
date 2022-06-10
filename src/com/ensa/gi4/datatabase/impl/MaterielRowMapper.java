package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() {
        };

        int idMateriel = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String code = resultSet.getString(3);
        String typeMateriel = resultSet.getString(4);
        boolean isDisponible = resultSet.getBoolean(5);
        boolean isAllouer = resultSet.getBoolean(6);


        materiel.setCode(code);
        materiel.setName(name);
        materiel.setIdMateriel(idMateriel);
        materiel.setTypeMateriel(typeMateriel);
        materiel.setDisponible(isDisponible);
        materiel.setAllouer(isAllouer);

        return materiel;
    }
}
