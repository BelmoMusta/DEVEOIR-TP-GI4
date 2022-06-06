package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
@Repository
public class UserDaoImpl extends GenericDAOuser<User> implements UserDao{
	    @Override
	    public User findUser(String name,String password) {
	        return super.findUser("SELECT * FROM users WHERE name=? and password=? ;",name,password);
	    }

	    @Override
	    protected UserRowMapper getRowMapper() { // template method design pattern
	        return new UserRowMapper();
	    }
}
