package com.ensa.gi4.modele;

public class User {
    public void setId(Long id) {
        this.id = id;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public User(Long id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }



    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    private Long id;
    private String userName;
    private String password;
    private String role;
    }
