package com.ensa.gi4.service.api;


import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface UserService {
    void init();
    boolean login(String username , String password);
    boolean isAdmin();
    List<Role> getUserAllRoles(User user);
    User getUserByName(String username);
    User getUserById(int id);
}
