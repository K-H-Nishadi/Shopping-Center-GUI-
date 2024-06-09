package com.ws.model;

import java.util.UUID;

public class User {

    private String userID;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public User() {
        this.userID = UUID.randomUUID().toString();
    }

    public User(String username, String password) {
        this.userID = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}
