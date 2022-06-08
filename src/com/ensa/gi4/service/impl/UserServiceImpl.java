package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.security.SessionHolder;
import com.ensa.gi4.service.api.I18nService;
import enums.LoginResponse;
import com.ensa.gi4.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final SessionHolder sessionHolder;
    private final I18nService i18nService;


    @Override
    public User addUser(String username, String email, String password) {
        User user = new User(username, email, passwordEncoder.encode(password));
        return userDao.add(user);

    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        User user =  this.userDao.findOneByUsername(username);
        if (user == null) return LoginResponse.USER_NOT_FOUND;

        if (passwordEncoder.matches(password, user.getPassword()) && !user.isLocked()){
            this.sessionHolder.setLoggedUser(user);
            this.sessionHolder.setValidity(LocalDateTime.now());
            return LoginResponse.SUCCESS;
        } else if (passwordEncoder.matches(password, user.getPassword()) && user.isLocked()){
            return LoginResponse.ACCOUNT_LOCKED;
        } else{
            return LoginResponse.PASSWORD_INCORRECT;
        }
    }

    @Override
    public void deleteUser(String username) {
        userDao.delete(username);
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public boolean isUserExpired() {
        return this.sessionHolder.isUserExpired();
    }

    @Override
    public User getLoggedUser(){
        return this.sessionHolder.getLoggedUser();
    }

    @Override
    public boolean isPasswordMatch(String password) {
        return this.passwordEncoder.matches(password, this.sessionHolder.getLoggedUser().getPassword());
    }

    @Override
    public void refreshValidity(){
        if(this.getLoggedUser() != null)
            this.sessionHolder.refreshValidity();
    }

    @Override
    public boolean hasRole(String role) {
        return getLoggedUser().getRoles().stream().anyMatch(r -> r.getName().equals(role));
    }

    @Override
    public void listUsers() {
        String[] cols = new String[]{"ID", "USERNAME", "EMAIL", "ROLES", "DATE", "LOCK"};
        String format = "%-5s %-20s %-50s %-50s %-20s %-10s\n";
        List<User> users = this.userDao.findAll();
        System.out.format(format, cols);
        for(User user : users){
            String[] fields = user.getFields();
            System.out.format(format, fields);
        }
    }

    @Override
    public void lockUser(int nextInt, boolean value) {
        if (this.getLoggedUser().getId().equals((long) nextInt)){
            System.out.println(i18nService.getText("message.wrong.lock.current"));
        }
        else if (userDao.findOneById((long) nextInt) != null){
            this.userDao.lock(nextInt, value);
            System.out.println(i18nService.getText("message.info.success"));
        }else {
            System.out.println(i18nService.getText("message.user.notfound"));
        }
    }
}