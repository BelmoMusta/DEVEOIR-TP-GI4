package com.ensa.gi4.service.impl;

import com.ensa.gi4.database.api.RoleDao;
import com.ensa.gi4.database.api.UserDao;
import com.ensa.gi4.database.api.UserRoleDao;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.UserRole;
import com.ensa.gi4.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("authentificationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${string.admin}")
    String adminString;

    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    User authenticatedUser;

    @Override
    public void authenticateWithUsernameAndPassword(String username, String password) {
        User user = userDao.findUserByUsernameAndPassword(username, password);
        if (user == null) return;
        user.setUserRoles(getUserRoles(user));
        initialiseUser(user);
    }

    @Override
    public List<Role> getUserRoles(User user) {
        List<Role> userRoles = new ArrayList<>();
        List<UserRole> roleLists = userRoleDao.findUserRoles(user.getId());
        for (UserRole userRole: roleLists) {
            Role role = roleDao.fineOne(userRole.getRoleId());
            userRoles.add(role);
        }
        return userRoles;
    }

    @Override
    public void initialiseUser(User user) {
        authenticatedUser.setId(user.getId());
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setFirstName(user.getFirstName());
        authenticatedUser.setLastName(user.getLastName());
        authenticatedUser.setUserRoles(user.getUserRoles());
    }

    @Override
    public boolean isUserAdmin() {
        for (Role role : authenticatedUser.getUserRoles()) {
            if (adminString.equalsIgnoreCase(role.getName()))
                return true;
        }
        return false;
    }

}
