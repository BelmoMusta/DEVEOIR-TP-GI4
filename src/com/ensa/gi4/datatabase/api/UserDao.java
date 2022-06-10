package com.ensa.gi4.datatabase.api;



import com.ensa.gi4.modele.User;

import java.util.List;

public interface UserDao {
    Boolean findbyuserinfo(String username , String password);
    User findUserByName(String username);
    User findOneById(int id);
    List<User> findAll();
}
