package com.ensa.gi4.modele;

public class User {
    private int user_id;
    private String username;
    private String hashed_password;
    private int role_id;

    //Related role table
    private Role role;






    public User() {
    }

    public User(String username, String hashed_password, int role_id) {
        this.username = username;
        this.hashed_password = hashed_password;
        this.role_id = role_id;
    }

    public User(int user_id, String username, String hashed_password, int role_id) {
        this.user_id = user_id;
        this.username = username;
        this.hashed_password = hashed_password;
        this.role_id = role_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                '}';
    }
}
