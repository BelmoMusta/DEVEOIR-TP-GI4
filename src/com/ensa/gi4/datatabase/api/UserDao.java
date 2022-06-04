package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findOneById(Long id);
    User findOneByUsername(String username);
    User add(User u);
    void delete(Integer id);
    void delete(String username);
}
