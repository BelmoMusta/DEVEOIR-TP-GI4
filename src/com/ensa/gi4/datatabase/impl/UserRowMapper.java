package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.*;

public class UserRowMapper  implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(); 
		String name = rs.getString("NAME");
		String password = rs.getString("Password");
		String role = rs.getString("ROLE");
		
		user.setName(name);
		user.setPassword(password);
		user.setRole(role);
		
		return user;
	}

}
