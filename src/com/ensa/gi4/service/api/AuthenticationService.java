package com.ensa.gi4.service.api;

import com.ensa.gi4.enums.Role;
import com.ensa.gi4.enums.UserCreateStatus;
import com.ensa.gi4.modele.User;

public interface AuthenticationService {
    String getPasswordHash(String password);
    User getUser(String hashedPassword, String username);
    boolean isPasswordValid(String hash,String password);
    UserCreateStatus createUser(String username, String password, Role role);

}
