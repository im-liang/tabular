package com.tabular.tabular.entity;

import java.util.Date;

public class Appointment2Customer {
    private long appointment2CustomerId;
    private long appointId;
    private long customerId;
    private Date time;
    public Appointment2Customer() {

    }

    public Appointment2Customer(long appointId, long customerId) {
        this.appointId = appointId;
        this.customerId = customerId;
    }

    public long getAppointId() {
        return appointId;
    }

    public void setAppointId(long appointId) {
        this.appointId = appointId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
