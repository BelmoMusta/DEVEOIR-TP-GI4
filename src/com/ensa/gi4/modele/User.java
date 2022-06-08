package com.ensa.gi4.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Role> roles;
    private Date registrationDate;
    private boolean locked;

    public User() {
    }

    //constructeur pour créer un utilisateur
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.locked = true;
        this.roles = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName(){
        return this.username;
    }

    public String[] getFields(){
        return new String[]{id.toString(), username,email, roles.toString(), registrationDate.toString(), String.valueOf(locked)};
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof User)) return false;
        else return this.getId().equals(((User) obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().intValue();
    }
}
