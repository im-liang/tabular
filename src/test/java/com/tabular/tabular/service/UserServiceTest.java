package com.tabular.tabular.service;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class UserServiceTest extends BaseTests {
    @Autowired
    private UserService userService;

    private long customerId, waiterId, ownerId;

    private void checkUserEquality(User user, String username, String password, int role, int status) {
        Assert.isTrue(user.getUsername().equals(username), "user username is not matched");
        Assert.isTrue(user.getPassword().equals(password), "user password is not matched");
        Assert.isTrue(user.getRole() == role, "user role is not matched");
        Assert.isTrue(user.getStatus() == status, "user status is not matched");
    }

    @Before
    public void initTest() {
        customerId = userService.createUserAsCustomer("customer", "password+c");
        waiterId = userService.createUserAsWaiter("waiter", "password+w");
        ownerId = userService.createUserAsOwner("owner", "password+o");
        userService.deactivateUser(waiterId);
    }

    @Test
    public void testCreateUser() throws Exception {
        User customer = userService.queryUserById(customerId);
        checkUserEquality(customer, "customer", "password+c", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        User waiter = userService.queryUserById(waiterId);
        checkUserEquality(waiter, "waiter", "password+w", UserRoleEnum.WAITER.getRole(), UserStatusEnum.INACTIVE.getStatus());
        User owner = userService.queryUserById(ownerId);
        checkUserEquality(owner, "owner", "password+o", UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void testDeactivateUser() throws Exception {
        boolean result = userService.deactivateUser(customerId);
        Assert.isTrue(result, "testDeactivateUser fail");
        User user = userService.queryUserById(customerId);
        Assert.isTrue(user.getStatus() == UserStatusEnum.INACTIVE.getStatus(), "testDeactivateUser fail");
    }

    @Test
    public void testActivateUser() throws Exception {
        boolean result = userService.activateUser(waiterId);
        Assert.isTrue(result, "testDeactivateUser fail");
        User user = userService.queryUserById(waiterId);
        Assert.isTrue(user.getStatus() == UserStatusEnum.ACTIVE.getStatus(), "testActivateUser fail");
    }

    @After
    public void cleanup() {
        userService.deleteUserById(customerId);
        userService.deleteUserById(waiterId);
        userService.deleteUserById(ownerId);
    }
}
