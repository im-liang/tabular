package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment2Customer;

import java.util.Date;
import java.util.List;

public interface Appointment2CustomerDao {
    int createRelation(long appointmentId, long customerId);
    List<Appointment2Customer> queryByAppointmentId(long appointmentId);
    List<Appointment2Customer> queryByCustomerId(long customerId);
    List<Appointment2Customer> queryPastRelations(Date datetime);
}
