package com.ensa.gi4.datatabase.api;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    List<User> login(User user);
    List<User> allouerMaterielIdUser(String name);
}
