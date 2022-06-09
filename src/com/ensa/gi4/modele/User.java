package com.ensa.gi4.modele;

public class User {


    private String userName;
    private String role;
    private Long userId;

    public Long getUserId() {

        return userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;

    }

    public String getUserName() {

        return this.userName;

    }

    public void setUserName(String userName) {

        this.userName = userName;

    }



    public String getRole() {

        return this.role;

    }

    public void setRole(String role) {

        this.role = role;

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
