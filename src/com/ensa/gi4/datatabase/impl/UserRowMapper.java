package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
    	    	
    	User user = new User() { // because it is abstract
        };
        
        int id = resultSet.getInt(1);
        String nom = resultSet.getString(2);
        String role = resultSet.getString(4);

        user.setId(id);
        user.setNom(nom);
        user.setRole(role);

        
    	return user;
    }

}
