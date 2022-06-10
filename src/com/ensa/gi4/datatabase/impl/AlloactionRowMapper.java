package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Allocation;

public class AlloactionRowMapper implements RowMapper<Allocation>{

	@Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
		Allocation allocation = new Allocation() { // because it is abstract
        };
        String username_ = resultSet.getString("USERNAME");
        String code_ = resultSet.getString("CODE");
        allocation.setCode(code_);
        allocation.setUsername(username_);
        return allocation;
    }
	
}
