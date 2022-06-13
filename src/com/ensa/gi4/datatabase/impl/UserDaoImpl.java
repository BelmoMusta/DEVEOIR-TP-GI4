package com.ensa.gi4.datatabase.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Utilisateur;

@Repository
public class UserDaoImpl extends GenericDAO<Utilisateur> implements UserDao{
	
	
	@Override
	public Utilisateur findUser(String username,String password) {
	return super.findOne("SELECT * FROM User WHERE username=? AND password=?;",new Object[] {username,password});
				
		
	}
	
	
	@Override
	protected RowMapper<Utilisateur> getRowMapper() {
		return new UserRowMapper();
	}



	}
	


