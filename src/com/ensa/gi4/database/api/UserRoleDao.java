package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.UserRole;

import java.util.List;

public interface UserRoleDao {
    List<UserRole> findUserRoles(Long userId);
}
