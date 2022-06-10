package com.ensa.gi4.database.impl.MaterialDao;

import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialRowMapper implements RowMapper<Material> {
    @Override
    public Material mapRow(ResultSet resultSet, int i) throws SQLException {

        Material material = new Material();

        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Long materielType = resultSet.getLong("material_type");
        int timeRented = resultSet.getInt("time_rented");
        int stock = resultSet.getInt("stock");
        boolean isAvailable = resultSet.getBoolean("available");

        material.setId(id);
        material.setName(name);
        material.setTimeRented(timeRented);
        material.setStock(stock);
        material.setAvailable(isAvailable);
        material.getMaterialType().setId(materielType);

        return material;
    }
}
