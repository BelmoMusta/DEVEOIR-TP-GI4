package com.ensa.gi4.datatabase.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ensa.gi4.modele.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		 User user = new User() { // because it is abstract 
			 
		 };       
		 
	        int idUser = resultSet.getInt(1);
	        String username = resultSet.getString(2);
	        String password = resultSet.getString(3);
	        String role = resultSet.getString(4);   
	        user.setIdUser(idUser);
	        user.setUsername(username);
	        user.setPassword(password);
	        user.setRole(role);

	        return user;
	        }
	}


