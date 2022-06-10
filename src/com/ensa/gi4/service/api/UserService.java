package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface UserService {

	Boolean findUser(User user);
	Boolean findRole(User user,String role);
	User findUserInfo(String username,String password);
}
