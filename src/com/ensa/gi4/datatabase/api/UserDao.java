package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.appuser.AppUser;

import java.util.List;

public interface UserDao {


    public List<AppUser> loginUser(AppUser user);
}
