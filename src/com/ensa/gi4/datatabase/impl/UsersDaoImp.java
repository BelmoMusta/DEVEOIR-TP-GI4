package com.ensa.gi4.datatabase.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UsersDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Users;

@Repository
public class UsersDaoImp extends GenericDAO<Users> implements UsersDao {

	@Override
	public List<Users> listUsers() {
		
        return super.findAll("SELECT * FROM USERS;");
		
	}

	@Override
	protected RowMapper<Users> getRowMapper() {
		// TODO Auto-generated method stub
		return new UsersRowMapper();
	}

	@Override
	public boolean verifyAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

}
