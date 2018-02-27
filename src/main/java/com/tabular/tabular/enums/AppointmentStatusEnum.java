package com.tabular.tabular.enums;

public enum AppointmentStatusEnum {
    CONFIRMED(0), CANCELLED(1), NO_SHOW(2), ARRIVED(3), FINISHED(4), IN_ROOM(5);

    private int status;

    private AppointmentStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
