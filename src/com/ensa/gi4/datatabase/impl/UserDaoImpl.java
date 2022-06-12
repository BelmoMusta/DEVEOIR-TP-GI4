package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{
	    @Override
	    public User findUser(String name,String password) {
	    	
	      // return super.findUser("SELECT * FROM users WHERE name=?  ;",name,password);
	    	User user;
	    	List<User> users=super.findAll("SELECT * FROM users where name='"+name+"';");
	    	
	    		for (int i = 0; i < users.size(); i++) {
					if (BCrypt.checkpw(password, users.get(i).getPassword())) {

						user = users.get(i);
						return user;
					}
				}
	    		return null;
	    	
	    }
	    @Override
	    public void afterPropertiesSet() { // from InitializingBean
	      
	    	 super.afterPropertiesSet();
	    	 hasherPassword();
	    }
	    @Override
	    protected UserRowMapper getRowMapper() { // template method design pattern
	        return new UserRowMapper();
	    }
	    // hasher le Password
	    @Override
		public void hasherPassword() {

	    	List<User> users=findAll();
	    	for(int i=0;i<users.size();i++) {
	    	String	hashPassword= BCrypt.hashpw(users.get(i).getPassword(), BCrypt.gensalt(10));
	    	  super.hasherPassword("update users set password=? where id=?;",hashPassword,users.get(i).getId());
	   
	    	}
	    
		}
	    
	    @Override
	    public List<User> findAll() {
	        return super.findAll("SELECT * FROM users;");
	    }
}
