package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

public interface UserDao {
    User findOneById(Integer id);
    User findOneByUsername(String username);

}
