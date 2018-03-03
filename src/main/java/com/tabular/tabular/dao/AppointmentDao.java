package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    int createAppointment(final Appointment appointment);
    Appointment queryAppointmentById(@Param("appointmentId") long appointmentId);
    List<Appointment> queryAppointmentByTimeRange(@Param("tableId") long tableId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    List<Appointment> queryAllPastAppointment(@Param("tableId") long tableId, @Param("dateTime") Date dateTime);
    List<Appointment> queryPastAppointmentWithStatus(@Param("tableId") long tableId, @Param("dateTime") Date dateTime, @Param("status") int status);
    List<Appointment> queryAllFutureAppointment(@Param("tableId") long tableId, @Param("dateTime") Date dateTime);
    List<Appointment> queryFutureAppointmentWithStatus(@Param("tableId") long tableId, @Param("dateTime") Date dateTime, @Param("status") int status);
    List<Appointment> queryAppointmentWithStatus(@Param("tableId") long tableId, @Param("status") int status);
    List<Appointment> queryAppointmentByTable(long tableId);
    List<Appointment> queryAll();
    int modifyAppointmentStatus(@Param("appointmentId") long appointmentId, @Param("status") int status);
    int deleteAppointmentById(long appointmentId);
}
