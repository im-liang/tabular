package com.tabular.tabular.service;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.entity.Waiter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class WaiterServiceTest extends BaseTests {
    @Autowired
    private WaiterService waiterService;

    private long waiterId;

    private void checkWaiterEquality(Waiter waiter, long waiterId, String name, long ownerId) {
        Assert.isTrue(waiter.getWaiterId() == waiterId, "waiter waiterId is not matched");
        Assert.isTrue(waiter.getName().equals(name), "waiter name is not matched");
        Assert.isTrue(waiter.getOwnerId() == ownerId, "waiter's ownerId is not matched");
    }

//    @Before
//    public void initTest() {
//        waiterId = waiterService.createWaiter("waiter");
//    }
//
//    @Test
//    public void testCreateCustomer() throws Exception {
//        Customer linkCustomer = waiterService.queryCustomerById(customerIdLinkUser);
//        checkWaiterEquality(linkCustomer, customerIdLinkUser, "customerWithLinkUser");
//        Customer newCustomer = waiterService.queryCustomerById(customerIdWithNewUser);
//        checkWaiterEquality(newCustomer, customerIdWithNewUser, "customerWithNewUser");
//    }
//
//    @Test
//    public void testModifyCustomerName() throws Exception {
//        waiterService.modifyCustomerName(customerIdLinkUser, "changed");
//        Customer linkCustomer = waiterService.queryCustomerById(customerIdLinkUser);
//        checkWaiterEquality(linkCustomer, customerIdLinkUser, "changed");
//    }
//
//    @After
//    public void cleanup() {
//        userService.deleteUserById(customerIdLinkUser);
//        userService.deleteUserById(customerIdWithNewUser);
//    }
}
