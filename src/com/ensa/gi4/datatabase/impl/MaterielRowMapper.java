package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MaterielRowMapper implements RowMapper<Material> {
    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        Material materiel = new Material();

        materiel.setName(resultSet.getString("name"));
        materiel.setMaterial_type(resultSet.getString("material_type"));
        materiel.setMaterial_id(resultSet.getInt("material_id"));
        materiel.setQuantity(resultSet.getInt("quantity"));

        return materiel;
    }
}
