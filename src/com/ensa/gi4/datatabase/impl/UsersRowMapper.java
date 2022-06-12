package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Users;

public class UsersRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet res, int i) throws SQLException {
		// TODO Auto-generated method stub
		  Users user = new Users();

		  	int id = res.getInt("id");
	        String username = res.getString("username");
	        String password = res.getString("password");
	        String role = res.getString("role");

	        user.setId(id);
	        user.setUsername(username);
	        user.setPassword(password);
	        user.setRole(role);
	        return user;
	    }	

}