package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AllocationRowMapper implements RowMapper<Allocation> {
    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation allocation = new Allocation();

        allocation.setAllocation_id(resultSet.getInt("allocation_id"));
        allocation.setDuration(resultSet.getInt("duration"));
        allocation.setMaterial_id(resultSet.getInt("material_id"));
        allocation.setUser_id(resultSet.getInt("user_id"));
        User user = new User(resultSet.getString("username"),resultSet.getString("hashed_password"),resultSet.getInt("role_id"));
        user.setRole( new Role(resultSet.getInt("role_id"),resultSet.getString(14)));
        allocation.setUser(user);
        allocation.setMaterial(new Material(resultSet.getString("name"),resultSet.getInt("quantity"),resultSet.getString("material_type") ));
        return allocation;
    }
}
