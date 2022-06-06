package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();
    List<Role> findUserAllRoles(int idUser);
    Role findOneById(int id);

}
