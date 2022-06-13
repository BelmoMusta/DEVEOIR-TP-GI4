package com.ensa.gi4.datatabase.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{

	@Override
	public User Auth(String nom, String passwd) {
		
		User user = super.Auth("SELECT * FROM USER WHERE USERNAME='"+nom+"' and PASSWORD='"+passwd+"';");

		if(user != null) {
			return user;
	    }else {
			return null;
		}
	}

	@Override
	protected RowMapper<User> getRowMapper() {
		return new UserRowMapper();
	}
}
