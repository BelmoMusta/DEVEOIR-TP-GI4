package com.ensa.gi4.datatabase.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{


	@Override
	public User findUser(String username,String password) {
		
		return super.findUser("SELECT * FROM users WHERE username= ? AND password= ?;", username,password);
	}

	@Override
	protected RowMapper<User> getRowMapper() {
	
		return new UserRowMapper();
	}

	@Override
	public String findRole(String username) {
		
		return super.findRole("SELECT role FROM users_roles WHERE username= ?;", username);
	}

	@Override
	protected RowMapper<Allocation> getRowMapperAllocation() {
		return null;
	}


	 
}
