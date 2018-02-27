package com.tabular.tabular.entity;

import java.util.Date;

public class Appointment {
    private long appointmentId;
    private Date datetime;
    private int status;
    private long tableId;

    public Appointment() {}

    public Appointment(Date datetime, int status, long tableId) {
        this.datetime = datetime;
        this.status = status;
        this.tableId = tableId;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
