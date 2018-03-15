package com.tabular.tabular.service;

import com.tabular.tabular.dao.Appointment2UserDao;
import com.tabular.tabular.entity.Appointment2User;
import com.tabular.tabular.exception.appointment.NoSuchAppointmentException;
import com.tabular.tabular.exception.relation.RelationAlreadyExistException;
import com.tabular.tabular.exception.user.NoSuchUserException;
import com.tabular.tabular.service.blueprint.Appointment2UserServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class Appointment2UserService implements Appointment2UserServiceBlueprint{

    private final Appointment2UserDao appointment2UserDao;
    private final AppointmentService appointmentService;
    private final UserService userService;

    @Autowired
    public Appointment2UserService(AppointmentService appointmentService, UserService userService, Appointment2UserDao appointment2UserDao) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.appointment2UserDao = appointment2UserDao;
    }

    @Override
    public long createRelation(long appointmentId, long userId) {
        if(!appointmentService.isAppointmentExist(appointmentId)) {
            throw new NoSuchAppointmentException("appointment with id: " + appointmentId + " does not exist");
        }
        if(!userService.isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }
        if(isRelationExist(appointmentId, userId)) {
            throw new RelationAlreadyExistException("relation with " + appointmentId + " and " + userId + "already exist");
        }
        Appointment2User relation = new Appointment2User(appointmentId, userId);
        appointment2UserDao.createRelation(relation);
        return relation.getAppointment2UserId();
    }

    @Override
    public Appointment2User queryRelationByRelationId(long appointment2userId) {
        Appointment2User relation = appointment2UserDao.queryRelationByRelationId(appointment2userId);
        if(relation == null) {
            throw new EntityNotFoundException("relation with " + appointment2userId + "does not exist");
        }
        return relation;
    }

    @Override
    public boolean isRelationExist(long appointmentId, long userId) {
        Appointment2User relation = appointment2UserDao.checkDuplicateRelation(appointmentId, userId);
        return relation != null;
    }

    @Override
    public List<Appointment2User> queryByAppointmentId(long appointmentId) {
        if(!appointmentService.isAppointmentExist(appointmentId)) {
            throw new NoSuchAppointmentException("appointment with id: " + appointmentId + " does not exist");
        }

        return appointment2UserDao.queryByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment2User> queryByUserId(long userId) {
        if(!userService.isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        return appointment2UserDao.queryByUserId(userId);
    }

    @Override
    public List<Appointment2User> queryPastRelations(Date datetime) {
        return appointment2UserDao.queryPastRelations(datetime);
    }

    @Override
    public List<Appointment2User> queryAll() {
        return appointment2UserDao.queryAll();
    }

    @Override
    public boolean deleteRelation(long appointment2userId) {
        int result = appointment2UserDao.deleteRelation(appointment2userId);
        return result == 1;
    }
}
