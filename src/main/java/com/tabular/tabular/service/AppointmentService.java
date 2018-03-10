package com.tabular.tabular.service;

import com.tabular.tabular.dao.AppointmentDao;
import com.tabular.tabular.entity.Appointment;
import com.tabular.tabular.service.blueprint.AppointmentServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements AppointmentServiceBlueprint {
    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public boolean createAppointment(Date datetime, int status, long tableId) {
        if(Validator.validateAppointmentStatus(status) && Validator.validateUserId(tableId)) {
            Appointment appointment = new Appointment(datetime, status, tableId);
            int result = appointmentDao.createAppointment(appointment);
            return result == 1;
        } else {
            return false;
        }
    }

    @Override
    public Appointment queryAppointmentById(long appointmentId) {
        if(Validator.validateUserId(appointmentId)) {
            return appointmentDao.queryAppointmentById(appointmentId);
        }else {
            return null;
        }

    }

    @Override
    public List<Appointment> queryAppointmentByTimeRange(long tableId, Date startTime, Date endTime) {
        if(Validator.validateUserId(tableId)) {
            return appointmentDao.queryAppointmentByTimeRange(tableId, startTime, endTime);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryAllPastAppointment(long tableId, Date dateTime) {
        if(Validator.validateUserId(tableId)) {
            return appointmentDao.queryAllPastAppointment(tableId, dateTime);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryPastAppointmentWithStatus(long tableId, Date dateTime, int status) {
        if(Validator.validateUserId(tableId) && Validator.validateAppointmentStatus(status)) {
            return appointmentDao.queryPastAppointmentWithStatus(tableId, dateTime, status);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryAllFutureAppointment(long tableId, Date dateTime) {
        if(Validator.validateUserId(tableId)) {
            return appointmentDao.queryAllFutureAppointment(tableId, dateTime);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryFutureAppointmentWithStatus(long tableId, Date dateTime, int status) {
        if(Validator.validateUserId(tableId) && Validator.validateAppointmentStatus(status)) {
            return appointmentDao.queryFutureAppointmentWithStatus(tableId, dateTime, status);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryAppointmentWithStatus(long tableId, int status) {
        if(Validator.validateUserId(tableId) && Validator.validateAppointmentStatus(status)) {
            return appointmentDao.queryAppointmentWithStatus(tableId, status);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryAppointmentByTable(long tableId) {
        if(Validator.validateUserId(tableId)) {
            return appointmentDao.queryAppointmentByTable(tableId);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment> queryAll() {
        return appointmentDao.queryAll();
    }

    @Override
    public boolean modifyAppointmentStatus(long appointmentId, int status) {
        if(Validator.validateUserId(appointmentId) && Validator.validateAppointmentStatus(status)) {
            int result = appointmentDao.modifyAppointmentStatus(appointmentId, status);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteAppointmentById(long appointmentId) {
        if(Validator.validateUserId(appointmentId)) {
            int result = appointmentDao.deleteAppointmentById(appointmentId);
            return result == 1;
        }else {
            return false;
        }
    }
}
