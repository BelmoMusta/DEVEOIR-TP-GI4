package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;
import enums.LoginResponse;

public interface UserService {
    User addUser(String username, String email, String password);
    LoginResponse loginUser(String username, String password);
    void deleteUser(String username);
    void deleteUser(int id);
    boolean isUserExpired();
    User getLoggedUser();
    boolean isPasswordMatch(String password);
    void refreshValidity();
    boolean hasRole(String role);
    void listUsers();
    void lockUser(int nextInt, boolean value);
}
