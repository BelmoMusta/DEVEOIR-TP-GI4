package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocations;
import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllocationsRowMapper implements RowMapper<Allocations> {
    @Override
    public Allocations mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocations allocation = new Allocations() ;
        int id = resultSet.getInt(1);
        int userID = resultSet.getInt(2);
        int materielID = resultSet.getInt(3);
        int availability = resultSet.getInt(4);
        allocation.setId(id);
        allocation.setUserID(userID);
        allocation.setId(materielID);
        allocation.setAvailability(availability);

        return allocation;

    }
}
