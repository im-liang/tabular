package com.tabular.tabular.entity;

import java.util.Date;

public class Appointment {
    private long appointmentId;
    private Date startTime;
    private int status;
    private long tableId;

    public Appointment() {}

    public Appointment(Date startTime, int status, long tableId) {
        this.startTime = startTime;
        this.status = status;
        this.tableId = tableId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }
}
