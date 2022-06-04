package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		
		User user= new User(); 
		String name = arg0.getString("name");
		String role = arg0.getString("role"); 
		
		user.setName(name);
		
		if(Role.ADMIN.toString().equals(role))
			user.setRole(Role.ADMIN);
		else {
			user.setRole(Role.USER);
		}
		
		return user;
	}

}
