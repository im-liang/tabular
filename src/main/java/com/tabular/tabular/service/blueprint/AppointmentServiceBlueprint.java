package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Appointment;

import java.util.Date;
import java.util.List;

public interface AppointmentServiceBlueprint {
    long createAppointment(Date datetime, int status, long tableId);
    Appointment queryAppointmentById(long appointmentId);
    List<Appointment> queryAppointmentByTimeRange(long tableId, Date startTime, Date endTime);
    List<Appointment> queryAllPastAppointment(long tableId, Date dateTime);
    List<Appointment> queryPastAppointmentWithStatus(long tableId, Date dateTime, int status);
    List<Appointment> queryAllFutureAppointment(long tableId, Date dateTime);
    List<Appointment> queryFutureAppointmentWithStatus(long tableId, Date dateTime, int status);
    List<Appointment> queryAppointmentWithStatus(long tableId, int status);
    List<Appointment> queryAppointmentByTable(long tableId);
    List<Appointment> queryAll();
    boolean modifyAppointmentStatus(long appointmentId, int status);
    boolean deleteAppointmentById(long appointmentId);
}
