package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

public interface UserDao {

	User Auth(String nom, String passwd);

}
