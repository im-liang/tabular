package com.tabular.tabular.dao;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.entity.User;
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

import java.util.List;

public class CustomerDaoTest extends BaseTests {
    @Autowired
    UserDao userDao;
    @Autowired
    CustomerDao customerDao;

    private long userId1;
    private long userId2;

    private void checkCustomerEquality(Customer customer, long customerId, String name) {
        System.out.println("customer id: " + customer.getCustomerId());
        Assert.isTrue(customer.getCustomerId() == customerId, "customer id not match");
        Assert.isTrue(customer.getName().equals(name), "customer name not match");
    }

    @Before
    public void initTest() {
        User user1 = new User("customer1", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user1);
        userId1 = user1.getUserId();

        User user2 = new User("customer2", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user2);
        userId2 = user2.getUserId();

        customerDao.insertCustomer(userId2, "customer2");
    }

    @Test
    public void testCreateCustomer() throws Exception {
        int result = customerDao.insertCustomer(userId1, "test");
        System.out.println(result);
    }

    @Test
    public void testQueryCustomerById() throws Exception {
        Customer customer = customerDao.queryCustomerById(userId2);
        checkCustomerEquality(customer, userId2, "customer2");
    }

    @Test
    public void testQueryCustomerByName() throws Exception {
        List<Customer> customer = customerDao.queryCustomerByName("customer2");
        Assert.isTrue(customer.size() == 1, "testQueryCustomerByName fail");
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Customer> customer = customerDao.queryAll();
        Assert.isTrue(customer.size() == 1, "testQueryCustomerByName fail");
    }

    @Test
    public void testModifyCustomerName() throws Exception {
        customerDao.modifyCustomerName(userId2, "yo");
        Customer customer = customerDao.queryCustomerById(userId2);
        checkCustomerEquality(customer, userId2, "yo");
    }

    @After
    public void cleanup() {
        userDao.deleteUserById(userId1);
        userDao.deleteUserById(userId2);
    }
}
