package com.ensa.gi4.datatabase.api;


import com.ensa.gi4.modele.Role;
import java.util.List;

public interface RoleDao {
    List<Role> findAll();
    Role findOneById(Long id);
    List<Role> findUserRoles(Long userId);
}
