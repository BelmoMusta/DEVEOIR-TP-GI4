package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielAllocatedRowMapper implements RowMapper<Allocation> {

    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation allocation = new Allocation() ;

        String username = resultSet.getString(1);
        String materiel = resultSet.getString(2);
        int quantity = resultSet.getInt(3);
        allocation.setUsername(username);
        allocation.setMateriel(materiel);
        allocation.setQuantity(quantity);

        return allocation;
    }
}
