package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Material> {
    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        Material materiel = new Material() { // because it is abstract
        };

        String name = resultSet.getString(2);
        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        String code = resultSet.getString(3);
        //materiel.setCode(code);
        materiel.setName(name);

        return materiel;
    }
}
