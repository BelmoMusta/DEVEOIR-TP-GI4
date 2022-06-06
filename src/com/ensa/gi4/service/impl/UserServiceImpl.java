package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.RoleDaoImpl;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDaoImpl userDao ;

    @Autowired
    RoleDaoImpl roleDao ;

    @Autowired
    User userCurrent ;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public boolean login(String username, String password) {
        return this.userDao.findbyuserinfo(username,password);
    }

    @Override
    public boolean isAdmin() {
        for(Role role : getUserAllRoles(userCurrent)){
            if("admin".equalsIgnoreCase(role.getName())){
                return true ;
            }
        }
        return false;
    }

    @Override
    public List<Role> getUserAllRoles(User user) {
        return this.roleDao.findUserAllRoles(user.getId());
    }

    @Override
    public User getUserByName(String username) {
        return userDao.findUserByName(username);
    }


    public void setUsercurrent(User u){
        this.userCurrent = u ;
    }
    public User getUsercurrent(){
        return this.userCurrent;
    }
}
