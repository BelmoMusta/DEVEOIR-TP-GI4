package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl extends GenericDAO<User>  implements UserDao {

	
	@Override
	protected UserRowMapper getRowMapper() {
		return new UserRowMapper(); 
	}

	@Override
	public User login(List<String> userData) {
		String query = "SELECT * FROM USER WHERE name=? AND password=?;"; 
		return super.findUser(query, userData); 
	}

}
