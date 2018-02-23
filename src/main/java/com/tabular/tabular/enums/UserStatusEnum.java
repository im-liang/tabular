package com.tabular.tabular.enums;

public enum UserStatusEnum {
    ACTIVE(0), INACTIVE(1);

    private int status;

    private UserStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
