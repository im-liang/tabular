package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Appointment2Customer;
import com.tabular.tabular.entity.User;
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

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:config/spring/dao.xml", "classpath:config/spring/service.xml" })
@SpringBootTest
public class Appointment2CustomerDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private TableDao tableDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @Autowired
    private Appointment2CustomerDao appointment2CustomerDao;

    private long customerId, appointmentId;

    private void checkRelationEquality(Appointment2Customer appointment2Customer, long appointmentId, long customerId) {
        Assert.isTrue(appointment2Customer.getAppointId() == appointmentId, "appointmentId is not matched");
        Assert.isTrue(appointment2Customer.getCustomerId() == customerId, "customerId is not matched");
    }

    @Before
    public void initTest() {
        customerDao.createUser("customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());

        customerId = customerDao.queryUserByUsername("customer").getUserId();

        restaurantDao.createRestaurant("restaurant", "w", "w", "w", "w", "w");
        long restaurantId = restaurantDao.queryRestaurantByName("restaurant").getRestaurantId();
        tableDao.insertTable("table", 3, restaurantId);
        long tableId = tableDao.queryRestaurantTableByName("table", restaurantId).getRestaurantId();
        appointmentDao.createAppointment(new Date(118, 9, 15), AppointmentStatusEnum.CONFIRMED.getStatus(), tableId);
        appointmentId = appointmentDao.queryAppointmentByTable(tableId).get(0).getAppointmentId();
    }

    @Test
    public void testCreateUser() throws Exception {
        int result = customerDao.createUser("test", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        System.out.println(result);
    }

    @After
    public void cleanup() {
        customerDao.deleteUserById(customerId);
        customerDao.deleteUserByName("owner");
        customerDao.deleteUserByName("waiter");
        customerDao.deleteUserByName("test");
    }
}
