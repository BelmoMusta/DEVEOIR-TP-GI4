package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllocationRowMapper implements RowMapper<Allocation> {
    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation allocation = new Allocation();

        allocation.setAllocation_id(resultSet.getInt("allocation_id"));
        allocation.setDuration(resultSet.getInt("duration"));
        allocation.setMaterial_id(resultSet.getInt("material_id"));
        allocation.setUser_id(resultSet.getInt("user_id"));

        return allocation;
    }
}
