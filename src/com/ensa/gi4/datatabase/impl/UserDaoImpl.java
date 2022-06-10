package com.ensa.gi4.datatabase.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl extends GenericDAO<User>  implements UserDao {

	@Value("${sql.user.login.query}")
	private String loginQuery;
	@Value("${sql.user.findAllUser.query}")
	private String findAllUserQuery;
	@Value("${sql.user.signUp.query}")
	private String signUpQuery;
	
	@Override
	protected UserRowMapper getRowMapper() {
		return new UserRowMapper(); 
	}	

	@Override
	public Optional<User> login(List<String> userData) {
		
	 	Optional<List<Map<String, Object>>> users = super.findAllUserAndPassword(findAllUserQuery); 
	 	User user; 
	 
	 	for (Map<String, Object> userInfo : users.get()) {
			if (BCrypt.checkpw(userData.get(1), (String) userInfo.get("PASSWORD")) && userData.get(0).equals((String) userInfo.get("NAME"))) {
				
				user = new User();
				user.setName((String) userInfo.get("NAME"));
				
				if(Role.ADMIN.toString().equals(userInfo.get("ROLE")))
					user.setRole(Role.ADMIN);
				else {
					user.setRole(Role.USER);
				}
				return 	Optional.of(user);
		
			}
		}
	 	
	 	return Optional.empty(); 
		
	}

	@Override
	public Optional<List<User>> findAll() {
		return super.findAllUser(findAllUserQuery);
	}

	@Override
	public int signUp(List<String> userData) {
		String passwordHashed = BCrypt.hashpw(userData.get(1), BCrypt.gensalt()); 
		return super.signUp(signUpQuery, userData.get(0), passwordHashed, Role.USER.toString()); 
	}

}
