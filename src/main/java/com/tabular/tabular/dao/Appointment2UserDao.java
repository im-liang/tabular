package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment2User;

import java.util.Date;
import java.util.List;

public interface Appointment2UserDao {
    /**
     * create a relation entry between appointment and user
     *
     * @param appointment2User
     * @return affected row
     */
    int createRelation(final Appointment2User appointment2User);

    /**
     * find list of relations based on appointment
     *
     * @param appointmentId
     * @return list of relations
     */
    List<Appointment2User> queryByAppointmentId(long appointmentId);

    /**
     * find list of relations based on user
     *
     * @param userId
     * @return list of relations
     */
    List<Appointment2User> queryByUserId(long userId);

    /**
     * find list of past relations
     *
     * @param datetime
     * @return list of relations
     */
    List<Appointment2User> queryPastRelations(Date datetime);

    /**
     * find all relations
     *
     * @return list of relations
     */
    List<Appointment2User> queryAll();

    /**
     * delete relation using the id
     *
     * @param appointment2userId
     * @return list of relations
     */
    int deleteRelation(long appointment2userId);
}
