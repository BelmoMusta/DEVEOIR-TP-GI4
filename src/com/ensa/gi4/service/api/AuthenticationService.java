package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface AuthenticationService {

    void authenticateWithUsernameAndPassword(String username, String password);
    List<Role> getUserRoles(User user);
    void initialiseUser(User user);
    boolean isUserAdmin();
}
