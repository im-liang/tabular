package com.tabular.tabular.entity;

public class Waiter {
    private long waiterId;
    private String name;
    private long ownerId;

    public Waiter() {
    }

    public Waiter(String name, long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    public long getWaiterId() {
        return waiterId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
