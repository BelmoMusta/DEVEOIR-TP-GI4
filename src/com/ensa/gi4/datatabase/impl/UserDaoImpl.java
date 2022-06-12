package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.User;


@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDAO {




	@Override
	protected RowMapper<User> getRowMapper() {
		return new UserRowMapper();
	}


	@Override
	public User userExist(String username, String password) {
		return super.userExist("SELECT * FROM USER WHERE username=? and password = ?",username,password );	}

	@Override
	public User login(String name, String password) {
		 try {
	            return super.findUser("SELECT * FROM USER WHERE userName=? and password=?;",name,password);
	        }
	       catch (Exception e){
	           System.out.println("login échoué");
	           System.exit(0);
	       }
	        return null;
	    }
	

	@Override
	public void register(String name, String password) {
		super.addUser("INSERT INTO USER(userName,password,role) values(?,?,'user');",name,password);
		
	}


	

	


	

	


	

}
