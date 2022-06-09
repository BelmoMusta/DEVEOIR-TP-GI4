package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();
    Role fineOne(Long id);

}
