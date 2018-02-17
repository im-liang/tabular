package com.tabular.tabular.entity;

public class User {
    private long id;
    private String lastName;
    private String firstName;
    private String email;
    private int phone;

    public User() {}

    public User(String email, int phone) {
        this.email = email;
        this.phone = phone;
    }
}
