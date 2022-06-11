package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.appuser.AppUser;
import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface UserDao {
    AppUser findOne(Long id);

    public List<AppUser> loginUser(AppUser user);
}
