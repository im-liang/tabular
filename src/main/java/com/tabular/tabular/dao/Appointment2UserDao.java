package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment2User;

import java.util.Date;
import java.util.List;

public interface Appointment2UserDao {
    int createRelation(final Appointment2User appointment2User);
    List<Appointment2User> queryByAppointmentId(long appointmentId);
    List<Appointment2User> queryByUserId(long userId);
    List<Appointment2User> queryPastRelations(Date datetime);
    List<Appointment2User> queryAll();
    int deleteRelation(long appointment2userId);
}
