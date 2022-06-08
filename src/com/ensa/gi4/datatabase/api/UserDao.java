package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

import java.util.List;

public interface UserDao {
    List<User> findUser(User user);

}
