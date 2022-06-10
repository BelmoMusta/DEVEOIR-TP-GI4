package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.User;

public interface UserDao {

    User findOne(Long id);
    User findUserByUsernameAndPassword(String username, String password);

}
