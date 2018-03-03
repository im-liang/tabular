package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment;
import com.tabular.tabular.entity.Table;
import com.tabular.tabular.enums.AppointmentStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:config/spring/dao.xml", "classpath:config/spring/service.xml" })
@SpringBootTest
public class AppointmentDaoTest {
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    TableDao tableDao;
    @Autowired
    AppointmentDao appointmentDao;

    long tableId, appointmentId;

    public void checkAppointmentEquality(Appointment appointment, int status, long tableId) {
        Assert.isTrue(appointment.getStatus() == status, "appointment status not match");
        Assert.isTrue(appointment.getTableId() == tableId, "appointment tableId not match");
    }

    @Before
    public void initTest() {
        restaurantDao.createRestaurant("restaurant", "w", "w", "w", "w", "w");
        long restaurantId = restaurantDao.queryRestaurantByName("restaurant").getRestaurantId();
        Table table = new Table("table", 3, restaurantId);
        tableDao.insertTable(table);
        tableId = table.getTableId();

        Appointment appointment = new Appointment(new Date(118, 9, 15), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        appointmentDao.createAppointment(appointment);
        appointmentId = appointment.getAppointmentId();
    }

    @Test
    public void testQueryAppointmentById() {
        Appointment appointment = appointmentDao.queryAppointmentById(appointmentId);
        checkAppointmentEquality(appointment, AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
    }

    @Test
    public void testQueryAppointmentByTimeRange() {
        List<Appointment> appointments = appointmentDao.queryAppointmentByTimeRange(tableId, new Date(118, 9, 1), new Date());
        Assert.isTrue(appointments.size() == 1, "testQueryAppointmentByTimeRange fail: 10/1/2018-now");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryAppointmentByTimeRange(tableId, new Date(117, 9, 1), new Date(117, 10, 1));
        Assert.isTrue(none.size() == 0, "testQueryAppointmentByTimeRange fail: 10/1/17-11/1/2017");
    }

    @Test
    public void testQueryAllPastAppointment() {
        List<Appointment> appointments = appointmentDao.queryAllPastAppointment(tableId, new Date());
        Assert.isTrue(appointments.size() == 1, "testQueryAllPastAppointment fail: now");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryAllPastAppointment(tableId, new Date(117, 9, 1));
        Assert.isTrue(none.size() == 0, "testQueryAllPastAppointment fail: 10/1/2017");
    }

    @Test
    public void testQueryPastAppointmentWithStatus() {
        List<Appointment> appointments = appointmentDao.queryPastAppointmentWithStatus(tableId, new Date(), AppointmentStatusEnum.CONFIRMED.getStatus());
        Assert.isTrue(appointments.size() == 1, "testQueryPastAppointmentWithStatus fail: now, confirm status");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryPastAppointmentWithStatus(tableId, new Date(), AppointmentStatusEnum.NO_SHOW.getStatus());
        Assert.isTrue(none.size() == 0, "testQueryPastAppointmentWithStatus fail: now, no show status");
        List<Appointment> noshow = appointmentDao.queryPastAppointmentWithStatus(tableId, new Date(117, 9, 1), AppointmentStatusEnum.NO_SHOW.getStatus());
        Assert.isTrue(noshow.size() == 0, "testQueryPastAppointmentWithStatus fail: 10/1/2017, no show status");
    }

    @Test
    public void testQueryAllFutureAppointment() {
        List<Appointment> appointments = appointmentDao.queryAllFutureAppointment(tableId, new Date(118, 9, 1));
        Assert.isTrue(appointments.size() == 1, "testQueryAllFutureAppointment fail: 10/1/2018");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryAllFutureAppointment(tableId, new Date());
        Assert.isTrue(none.size() == 0, "testQueryAllFutureAppointment fail: now");
    }

    @Test
    public void testQueryFutureAppointmentWithStatus() {
        List<Appointment> appointments = appointmentDao.queryFutureAppointmentWithStatus(tableId, new Date(118, 9, 1), AppointmentStatusEnum.CONFIRMED.getStatus());
        Assert.isTrue(appointments.size() == 1, "testQueryPastAppointmentWithStatus fail: 10/1/2018, confirm status");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> noneNoShow = appointmentDao.queryFutureAppointmentWithStatus(tableId, new Date(118, 9, 1), AppointmentStatusEnum.NO_SHOW.getStatus());
        Assert.isTrue(noneNoShow.size() == 0, "testQueryPastAppointmentWithStatus fail: 10/1/2018, no show status");
        List<Appointment> none = appointmentDao.queryFutureAppointmentWithStatus(tableId, new Date(), AppointmentStatusEnum.NO_SHOW.getStatus());
        Assert.isTrue(none.size() == 0, "testQueryPastAppointmentWithStatus fail: now, no show status");
    }

    @Test
    public void testQueryAppointmentWithStatus() {
        List<Appointment> appointments = appointmentDao.queryAppointmentWithStatus(tableId, AppointmentStatusEnum.CONFIRMED.getStatus());
        Assert.isTrue(appointments.size() == 1, "testQueryAppointmentWithStatus fail: confirm status");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryAppointmentWithStatus(tableId, AppointmentStatusEnum.NO_SHOW.getStatus());
        Assert.isTrue(none.size() == 0, "testQueryAppointmentWithStatus fail: no show status");
    }

    @Test
    public void testQueryAppointmentByTable() {
        List<Appointment> appointments = appointmentDao.queryAppointmentByTable(tableId);
        Assert.isTrue(appointments.size() == 1, "testQueryAppointmentByTable fail: exist");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        List<Appointment> none = appointmentDao.queryAppointmentByTable(-1);
        Assert.isTrue(none.size() == 0, "testQueryAppointmentByTable fail: meant to fail");
    }

    @Test
    public void testQueryAll() {
        List<Appointment> appointments = appointmentDao.queryAll();
        Assert.isTrue(appointments.size() == 1, "testQueryAll fail");
        checkAppointmentEquality(appointments.get(0), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
    }

    @Test
    public void testModifyAppointmentStatus() {
        appointmentDao.modifyAppointmentStatus(appointmentId, AppointmentStatusEnum.ARRIVED.getStatus());
        checkAppointmentEquality(appointmentDao.queryAppointmentById(appointmentId), AppointmentStatusEnum.ARRIVED.getStatus(), tableId);
    }

    @Test
    public void testDeleteAppointmentById() {
        appointmentDao.deleteAppointmentById(appointmentId);
        Assert.isTrue(appointmentDao.queryAll().size() == 0, "testDeleteAppointmentById fail");
    }

    @After
    public void cleanup() {
        appointmentDao.deleteAppointmentById(appointmentId);
        restaurantDao.deleteRestaurantByName("restaurant");
    }
}
