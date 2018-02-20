package com.tabular.tabular.entity;

import java.util.Date;

public class Appointment2Customer {
    private long appointment2CustomerId;
    private long appointId;
    private long waiterId;
    private Date time;
    public Appointment2Customer() {

    }

    public Appointment2Customer(long appointId, long waiterId) {
        this.appointId = appointId;
        this.waiterId = waiterId;
    }

    public long getAppointId() {
        return appointId;
    }

    public void setAppointId(long appointId) {
        this.appointId = appointId;
    }

    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
