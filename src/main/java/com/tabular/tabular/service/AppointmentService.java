package com.tabular.tabular.service;

import com.tabular.tabular.dao.AppointmentDao;
import com.tabular.tabular.entity.Appointment;
import com.tabular.tabular.exception.appointment.NoSuchAppointmentException;
import com.tabular.tabular.exception.table.NoSuchTableException;
import com.tabular.tabular.service.blueprint.AppointmentServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements AppointmentServiceBlueprint {

    private final AppointmentDao appointmentDao;
    private final TableService tableService;

    @Autowired
    public AppointmentService(TableService tableService, AppointmentDao appointmentDao) {
        this.tableService = tableService;
        this.appointmentDao = appointmentDao;
    }

    @Override
    public long createAppointment(Date datetime, int status, long tableId) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }

        Appointment appointment = new Appointment(datetime, status, tableId);
        appointmentDao.createAppointment(appointment);
        return appointment.getAppointmentId();
    }

    @Override
    public Appointment queryAppointmentById(long appointmentId) {
        Appointment appointment = appointmentDao.queryAppointmentById(appointmentId);
        if(appointment == null) {
            throw new NoSuchTableException("appointment with id: " + appointmentId + " does not exist");
        }

        return appointment;
    }

    @Override
    public List<Appointment> queryAppointmentByTimeRange(long tableId, Date startTime, Date endTime) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }

        return appointmentDao.queryAppointmentByTimeRange(tableId, startTime, endTime);
    }

    @Override
    public List<Appointment> queryAllPastAppointment(long tableId, Date dateTime) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }
        return appointmentDao.queryAllPastAppointment(tableId, dateTime);
    }

    @Override
    public List<Appointment> queryPastAppointmentWithStatus(long tableId, Date dateTime, int status) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }

        return appointmentDao.queryPastAppointmentWithStatus(tableId, dateTime, status);
    }

    @Override
    public List<Appointment> queryAllFutureAppointment(long tableId, Date dateTime) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }
        return appointmentDao.queryAllFutureAppointment(tableId, dateTime);
    }

    @Override
    public List<Appointment> queryFutureAppointmentWithStatus(long tableId, Date dateTime, int status) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }

        return appointmentDao.queryFutureAppointmentWithStatus(tableId, dateTime, status);
    }

    @Override
    public List<Appointment> queryAppointmentWithStatus(long tableId, int status) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }

        return appointmentDao.queryAppointmentWithStatus(tableId, status);
    }

    @Override
    public List<Appointment> queryAppointmentByTable(long tableId) {
        if(tableService.isTableExist(tableId)) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }
        return appointmentDao.queryAppointmentByTable(tableId);
    }

    @Override
    public List<Appointment> queryAll() {
        return appointmentDao.queryAll();
    }

    @Override
    public boolean modifyAppointmentStatus(long appointmentId, int status) {
        if(!isAppointmentExist(appointmentId)) {
            throw new NoSuchAppointmentException("appointment with id: " + appointmentId + " does not exist");
        }

        int result = appointmentDao.modifyAppointmentStatus(appointmentId, status);
        return result == 1;
    }

    @Override
    public boolean deleteAppointmentById(long appointmentId) {
        if(!isAppointmentExist(appointmentId)) {
            throw new NoSuchAppointmentException("appointment with id: " + appointmentId + " does not exist");
        }
        int result = appointmentDao.deleteAppointmentById(appointmentId);
        return result == 1;
    }

    public boolean isAppointmentExist(long appointmentId) {
        return appointmentDao.queryAppointmentById(appointmentId) != null;
    }
}
