package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Appointment2User;

import java.util.Date;
import java.util.List;

public interface Appointment2UserServiceBlueprint {
    /**
     * create an appointment to user relation
     *
     * @param appointmentId
     * @param userId
     * @return boolean indicate if such relation is created
     */
    boolean createRelation(long appointmentId, long userId);

    /**
     * find a list of relations associated with the appointment id
     *
     * @param appointmentId
     * @return list of such relations
     */
    List<Appointment2User> queryByAppointmentId(long appointmentId);

    /**
     * find a list of relations associated with the user id
     *
     * @param userId
     * @return list of such relations
     */
    List<Appointment2User> queryByUserId(long userId);

    /**
     * find a list of past relations before datetime
     *
     * @param datetime
     * @return list of such relations
     */
    List<Appointment2User> queryPastRelations(Date datetime);

    /**
     * find a list of all relations
     *
     * @return list of such relations
     */
    List<Appointment2User> queryAll();

    /**
     * delete an appointment to user relation associated with the appointment2user id
     *
     * @param appointment2userId
     * @return boolean indicate if such relation is deleted
     */
    boolean deleteRelation(long appointment2userId);
}
