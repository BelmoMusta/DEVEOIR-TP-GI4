package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

public interface UserDao {
    User findUser(String username, String password);
}
