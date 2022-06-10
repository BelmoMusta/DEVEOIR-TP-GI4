package com.ensa.gi4.database.impl.MaterialTypeDao;

import com.ensa.gi4.modele.MaterialType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialTypeRowMapper implements RowMapper<MaterialType> {

    @Override
    public MaterialType mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new MaterialType(id, name);
    }
}
