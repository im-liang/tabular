package com.tabular.tabular.entity;

public class Owner {
    private long ownerId;
    private String lastName;
    private String firstName;
    private long restaurantId;

    public Owner() {

    }

    public Owner(String lastName, String firstName, long restaurantId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.restaurantId = restaurantId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
