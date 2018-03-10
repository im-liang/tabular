package com.tabular.tabular.service;

import com.tabular.tabular.dao.Appointment2UserDao;
import com.tabular.tabular.entity.Appointment2User;
import com.tabular.tabular.service.blueprint.Appointment2UserServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Appointment2UserService implements Appointment2UserServiceBlueprint{
    @Autowired
    private Appointment2UserDao appointment2UserDao;

    @Override
    public boolean createRelation(long appointmentId, long userId) {
        if(Validator.validateUserId(appointmentId) && Validator.validateUserId(userId)) {
            Appointment2User relation = new Appointment2User(appointmentId, userId);
            int result = appointment2UserDao.createRelation(relation);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public List<Appointment2User> queryByAppointmentId(long appointmentId) {
        if(Validator.validateUserId(appointmentId)) {
            return appointment2UserDao.queryByAppointmentId(appointmentId);
        }else {
            return null;
        }
    }

    @Override
    public List<Appointment2User> queryByUserId(long userId) {
        if(Validator.validateUserId(userId)) {
            return appointment2UserDao.queryByUserId(userId);
        }else {
            return null;
        }
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
        if(Validator.validateUserId(appointment2userId)) {
            int result = appointment2UserDao.deleteRelation(appointment2userId);
            return result == 1;
        }else {
            return false;
        }
    }
}
