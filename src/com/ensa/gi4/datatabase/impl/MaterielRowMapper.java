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

        String name = resultSet.getString("name");
        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        String code = resultSet.getString("code");
        int quantite = resultSet.getInt("quantite");
        boolean disponible = resultSet.getBoolean("disponible");
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setQuantite(quantite);
        materiel.setDisponible(disponible);

        return materiel;
    }
}
