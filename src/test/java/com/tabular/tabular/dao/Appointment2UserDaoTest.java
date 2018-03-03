package com.tabular.tabular.dao;

import com.tabular.tabular.entity.*;
import com.tabular.tabular.enums.AppointmentStatusEnum;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
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
public class Appointment2UserDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private TableDao tableDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @Autowired
    private Appointment2UserDao appointment2UserDao;

    private long userId, appointmentId, relationId;

    private void checkRelationEquality(Appointment2User appointment2User, long relationId, long appointmentId, long userId) {
        Assert.isTrue(appointment2User.getAppointment2UserId() == relationId, "relationId is not matched");
        Assert.isTrue(appointment2User.getAppointmentId() == appointmentId, "appointmentId is not matched");
        Assert.isTrue(appointment2User.getUserId() == userId, "userId is not matched");
    }

    @Before
    public void initTest() {
        User user = new User("customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        userId = user.getUserId();

        Restaurant restaurant = new Restaurant("restaurant", "w", "w", "w", "w", "w");
        restaurantDao.createRestaurant(restaurant);
        long restaurantId = restaurant.getRestaurantId();

        Table table = new Table("table", 3, restaurantId);
        tableDao.insertTable(table);
        long tableId = table.getTableId();

        Appointment appointment = new Appointment(new Date(118, 9, 15), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        appointmentDao.createAppointment(appointment);
        appointmentId = appointment.getAppointmentId();

        Appointment2User relation = new Appointment2User(appointmentId, userId);
        appointment2UserDao.createRelation(relation);
        relationId = relation.getAppointment2UserId();
    }

    @Test
    public void testQueryByAppointmentId() throws Exception {
        List<Appointment2User> relations = appointment2UserDao.queryByAppointmentId(appointmentId);
        Assert.isTrue(relations.size() == 1, "testQueryByAppointmentId fail");
        checkRelationEquality(relations.get(0), relationId, appointmentId, userId);
    }

    @Test
    public void testQueryByUserId() throws Exception {
        List<Appointment2User> relations = appointment2UserDao.queryByUserId(userId);
        Assert.isTrue(relations.size() == 1, "testQueryByUserId fail");
        checkRelationEquality(relations.get(0), relationId, appointmentId, userId);
    }

    @Test
    public void testPastRelations() throws Exception {
        List<Appointment2User> relations = appointment2UserDao.queryPastRelations(new Date());
        Assert.isTrue(relations.size() == 1, "testPastRelations fail");
        checkRelationEquality(relations.get(0), relationId, appointmentId, userId);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Appointment2User> relations = appointment2UserDao.queryAll();
        Assert.isTrue(relations.size() == 1, "testQueryAll fail");
        checkRelationEquality(relations.get(0), relationId, appointmentId, userId);
    }

    @After
    public void cleanup() {
        appointment2UserDao.deleteRelation(relationId);
        userDao.deleteUserById(userId);
        appointmentDao.deleteAppointmentById(appointmentId);
        restaurantDao.deleteRestaurantByName("restaurant");
    }
}
