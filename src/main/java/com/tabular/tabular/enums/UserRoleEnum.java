package com.tabular.tabular.enums;

public enum UserRoleEnum {
    CUSTOMER(0), OWNER(1), WAITER(2);

    private int role;

    private UserRoleEnum(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
