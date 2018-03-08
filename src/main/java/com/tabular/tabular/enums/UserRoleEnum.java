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

    public static boolean isRoleValid(int role) {
        if(role >= 0 && role <= 2) {
            return true;
        }else {
            return false;
        }
    }
}
