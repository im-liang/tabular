package com.tabular.tabular.entity;

import java.util.Date;

public class Appointment2User {
    private long appointment2UserId;
    private long appointmentId;
    private long userId;
    private Date datetime;
    public Appointment2User() {

    }

    public Appointment2User(long appointmentId, long userId) {
        this.appointmentId = appointmentId;
        this.userId = userId;
    }

    public long getAppointment2UserId() {
        return appointment2UserId;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
