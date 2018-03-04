package com.tabular.tabular.service;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CustomerServiceTest extends BaseTests {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    private long customerIdWithNewUser, customerIdLinkUser;

    private void checkCustomerEquality(Customer customer, long customerId, String name) {
        Assert.isTrue(customer.getCustomerId() == customerId, "customer customerId is not matched");
        Assert.isTrue(customer.getName().equals(name), "customer name is not matched");
    }

    @Before
    public void initTest() {
        customerIdWithNewUser = customerService.createCustomer("customerWithNewUser");
        userService.createUserAsCustomer("customerWithLinkUser", "password");
        customerIdLinkUser = customerService.createCustomer("customerWithLinkUser");
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer linkCustomer = customerService.queryCustomerById(customerIdLinkUser);
        checkCustomerEquality(linkCustomer, customerIdLinkUser, "customerWithLinkUser");
        Customer newCustomer = customerService.queryCustomerById(customerIdWithNewUser);
        checkCustomerEquality(newCustomer, customerIdWithNewUser, "customerWithNewUser");
    }

    @Test
    public void testModifyCustomerName() throws Exception {
        customerService.modifyCustomerName(customerIdLinkUser, "changed");
        Customer linkCustomer = customerService.queryCustomerById(customerIdLinkUser);
        checkCustomerEquality(linkCustomer, customerIdLinkUser, "changed");
    }

    @After
    public void cleanup() {
        userService.deleteUserById(customerIdLinkUser);
        userService.deleteUserById(customerIdWithNewUser);
    }
}
