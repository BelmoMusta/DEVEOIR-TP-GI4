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

        int idM = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String code = resultSet.getString(3);
        int qte = resultSet.getInt(4);
        boolean disponible = resultSet.getBoolean(5);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setQte(qte);
        materiel.setIdM(idM);
        materiel.setDisponible(disponible);

        return materiel;
    }
}
