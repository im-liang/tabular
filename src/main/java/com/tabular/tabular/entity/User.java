package com.tabular.tabular.entity;

public class User {
    private long userId;
    private String username;
    private String password;
    private int role;
    private int status;

    public User() {}

    public User(String username, String password, int role, int status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
