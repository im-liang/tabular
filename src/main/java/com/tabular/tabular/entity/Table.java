package com.tabular.tabular.entity;

public class Table {
    private long tableId;
    private String name;
    private int numberLimit;
    private long restaurantId;

    public Table() {}

    public Table(String name, int numberLimit, long restaurantId) {
        this.name = name;
        this.numberLimit = numberLimit;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(int numberLimit) {
        this.numberLimit = numberLimit;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
