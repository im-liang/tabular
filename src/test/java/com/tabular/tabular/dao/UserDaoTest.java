package com.tabular.tabular.dao;

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

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:config/spring/dao.xml", "classpath:config/spring/service.xml" })
@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    private long customerId;

    private void checkUserEquality(User user, String username, String password, int role, int status) {
        System.out.println("restaurant id: " + user.getUserId());
        Assert.isTrue(user.getUsername().equals(username), "user username is not matched");
        Assert.isTrue(user.getPassword().equals(password), "user password is not matched");
        Assert.isTrue(user.getRole() == role, "user role is not matched");
        Assert.isTrue(user.getStatus() == status, "user status is not matched");
    }

    @Before
    public void initTest() {
        userDao.createUser(new User("customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus()));
        userDao.createUser(new User("owner", "password", UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus()));
        userDao.createUser(new User("waiter", "password", UserRoleEnum.WAITER.getRole(), UserStatusEnum.ACTIVE.getStatus()));

        customerId = userDao.queryUserByUsername("customer").getUserId();
    }

    @Test
    public void testCreateUser() throws Exception {
        int result = userDao.createUser(new User("test", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus()));
        System.out.println(result);
    }

    @Test
    public void testQueryUserById() throws Exception {
        User user = userDao.queryUserById(customerId);
        checkUserEquality(user, user.getUsername(), user.getPassword(), user.getRole(), user.getStatus());
    }

    @Test
    public void testQueryUserByUsername() throws Exception {
        User user = userDao.queryUserByUsername("customer");
        checkUserEquality(user, user.getUsername(), user.getPassword(), user.getRole(), user.getStatus());
    }

    @Test
    public void testQueryUserByRole() throws Exception {
        List<User> users = userDao.queryUserByRole(UserRoleEnum.OWNER.getRole());
        Assert.isTrue(users.size() == 1, "testQueryUserByRole fail");
    }

    @Test
    public void testQueryUserByStatus() throws Exception {
        List<User> users = userDao.queryUserByStatus(UserStatusEnum.ACTIVE.getStatus());
        Assert.isTrue(users.size() == 3, "testQueryUserByStatus fail");
    }

    @Test
    public void testQueryAll() throws Exception {
        List<User> users = userDao.queryAll();
        Assert.isTrue(users.size() == 3, "testQueryAll fail");
    }

    @Test
    public void testModifyUsername() throws Exception {
        userDao.modifyUsername(customerId, "hello");
        User user = userDao.queryUserById(customerId);
        Assert.isTrue(user.getUsername().equals("hello"), "testModifyUsername fail");
    }

    @Test
    public void testModifyUserPassword() throws Exception {
        userDao.modifyUserPassword(customerId, "yoyoyo");
        User user = userDao.queryUserById(customerId);
        Assert.isTrue(user.getPassword().equals("yoyoyo"), "testModifyUserPassword fail");
    }

    @Test
    public void testModifyUserRole() throws Exception {
        userDao.modifyUserRole(customerId, UserRoleEnum.OWNER.getRole());
        User user = userDao.queryUserById(customerId);
        Assert.isTrue(user.getRole() == UserRoleEnum.OWNER.getRole(), "testModifyUserPassword fail");
    }

    @Test
    public void testModifyUserStatus() throws Exception {
        userDao.modifyUserStatus(customerId, UserStatusEnum.INACTIVE.getStatus());
        User user = userDao.queryUserById(customerId);
        Assert.isTrue(user.getStatus() == UserStatusEnum.INACTIVE.getStatus(), "testModifyUserPassword fail");
    }

    @After
    public void cleanup() {
        userDao.deleteUserById(customerId);
        userDao.deleteUserByName("owner");
        userDao.deleteUserByName("waiter");
        userDao.deleteUserByName("test");
    }
}
