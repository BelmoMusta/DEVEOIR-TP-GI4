package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Material> {
    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        Material material = new Material() {
        };


        material.setMaterialId(resultSet.getLong("MATERIALID"));
        material.setName(resultSet.getString("NAME"));
        material.setMaterialType(resultSet.getString("MATERIALTYPE"));
        material.setAvailable(resultSet.getBoolean("AVAILABILITY"));
        return material;
    }
}

