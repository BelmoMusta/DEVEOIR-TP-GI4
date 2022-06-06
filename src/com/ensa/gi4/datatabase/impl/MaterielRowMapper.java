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
        int id = resultSet.getInt("id");
        String code = resultSet.getString("code");
        int quantite = resultSet.getInt("quantite");
        boolean disponible = resultSet.getBoolean("disponible");
        
        materiel.setId(id);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setQuantite(quantite);
        materiel.setDisponible(disponible);

        return materiel;
    }
}
