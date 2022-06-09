package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

import java.util.List;

public interface UserDao {

    User findUser(String userName, String password);
    List<User> findAllUsers();


}
