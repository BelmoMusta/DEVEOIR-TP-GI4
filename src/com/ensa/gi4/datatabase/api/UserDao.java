package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

public interface UserDao {
    User findUserByUsernameAndPassword(String username,String password);
    User findOne(Long id);
}
