package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface SignInUserService {
    public void signInWithUsernameAndPassword(String username,String password);
    public boolean isAdmin(User user);
    public void setCurrentUser(User user);
    public User getCurrentUser();
    public String getUserRole(User user);
}
