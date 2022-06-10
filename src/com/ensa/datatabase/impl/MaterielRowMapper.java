package com.ensa.datatabase.impl;

import com.ensa.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };

        String name = resultSet.getString(2);
        String name_ = resultSet.getString("NAME");
        String code_ = resultSet.getString("CODE");
        String code = resultSet.getString(3);
        materiel.setCode(code_);
        materiel.setName(name_);

        return materiel;
    }
}
