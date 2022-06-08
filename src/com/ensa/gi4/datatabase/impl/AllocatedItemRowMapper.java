package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.AllocatedItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllocatedItemRowMapper implements RowMapper<AllocatedItem> {
    @Override
    public AllocatedItem mapRow(ResultSet resultSet, int i) throws SQLException {
        AllocatedItem a = new AllocatedItem();
        a.setId_user(resultSet.getInt("id_user"));
        a.setId_materiel(resultSet.getInt("id_materiel"));
        return a;
    }
}
