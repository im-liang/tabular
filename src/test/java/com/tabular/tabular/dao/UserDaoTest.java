package com.tabular.tabular.dao;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class UserDaoTest extends BaseTests {
    @Autowired
    UserDao userDao;
    private long userId;

    private void checkUserEquality(User user, String username, String password, int role, int status) {
        Assert.isTrue(user.getUsername().equals(username), "user username is not matched");
        Assert.isTrue(user.getPassword().equals(password), "user password is not matched");
        Assert.isTrue(user.getRole() == role, "user role is not matched");
        Assert.isTrue(user.getStatus() == status, "user status is not matched");
    }

    @Before
    public void initTest() {
        User user = new User("customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        userId = user.getUserId();
    }

    @Test
    public void createUser_duplicateUsername_return0() throws Exception {
        User user = new User("customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        int result = userDao.createUser(user);
        Assert.isTrue(result == 0, "createUser_duplicateUsername_return0: insert duplicated");
    }

    @Test
    public void createUser_uniqueUsername_return1() throws Exception {
        User user = new User("test", "pass", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        int result = userDao.createUser(user);
        Assert.isTrue(result == 1, "createUser_uniqueUsername_return1: insert failed");
        checkUserEquality(user, "test", "pass", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void queryUserById_invalidId_returnNull() throws Exception {
        User user = userDao.queryUserById(-1);
        Assert.isTrue(user == null, "queryUserById_invalidId_returnNull: return failed");
    }

    @Test
    public void queryUserById_validId_returnUser() throws Exception {
        User user = userDao.queryUserById(userId);
        Assert.isTrue(user != null, "queryUserById_validId_returnUser: return failed");
        checkUserEquality(user, "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void queryUserByUsername_invalidUsername_returnNull() throws Exception {
        User user = userDao.queryUserByUsername("");
        Assert.isTrue(user == null, "queryUserByUsername_validUsername_returnNull: return failed");
    }

    @Test
    public void queryUserByUsername_validUsername_returnUser() throws Exception {
        User user = userDao.queryUserByUsername("customer");
        Assert.isTrue(user != null, "queryUserByUsername_validUsername_returnNull: return failed");
        checkUserEquality(user, "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void queryUserByRole_invalidRole_returnEmptyList() throws Exception {
        List<User> users = userDao.queryUserByRole(-1);
        Assert.isTrue(users.size() == 0, "queryUserByRole_invalidRole_returnEmptyList: search not match");
    }

    @Test
    public void queryUserByRole_validRole_returnList() throws Exception {
        List<User> users = userDao.queryUserByRole(UserRoleEnum.CUSTOMER.getRole());
        Assert.isTrue(users.size() == 1, "queryUserByRole_validRole_returnList: search fail");
        checkUserEquality(users.get(0), "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void queryUserByStatus_invalidStatus_returnEmptyList() throws Exception {
        List<User> users = userDao.queryUserByStatus(-1);
        Assert.isTrue(users.size() == 0, "queryUserByStatus_invalidStatus_returnEmptyList: search fail");
    }

    @Test
    public void queryUserByStatus_validStatus_returnList() throws Exception {
        List<User> users = userDao.queryUserByStatus(UserStatusEnum.ACTIVE.getStatus());
        Assert.isTrue(users.size() == 1, "queryUserByStatus_validStatus_returnList: search fail");
        checkUserEquality(users.get(0), "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void queryAll() throws Exception {
        List<User> users = userDao.queryAll();
        Assert.isTrue(users.size() == 1, "queryAll fail");
        checkUserEquality(users.get(0), "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void modifyUsername_InvalidId_return0() throws Exception {
        int affectedRow = userDao.modifyUsername(-1, "hello");
        Assert.isTrue(affectedRow == 0, "modifyUsername_InvalidId_return0 fail");
    }

    @Test
    public void modifyUsername_validId_return1() throws Exception {
        int affectedRow = userDao.modifyUsername(userId, "hello");
        Assert.isTrue(affectedRow == 1, "modifyUsername_validUsernameAndId_return1 fail");
        User user = userDao.queryUserById(userId);
        checkUserEquality(user, "hello", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void modifyUserPassword_invalidId_return0() throws Exception {
        int affectRow = userDao.modifyUserPassword(-1, "yoyoyo");
        Assert.isTrue(affectRow == 0, "modifyUserPassword_invalidId_return0 fail");
    }

    @Test
    public void modifyUserPassword_validId_return1() throws Exception {
        userDao.modifyUserPassword(userId, "yoyoyo");
        User user = userDao.queryUserById(userId);
        checkUserEquality(user, "customer", "yoyoyo", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
    }

    @Test
    public void modifyUserRole_invalidId_return0() throws Exception {
        int affectRow = userDao.modifyUserRole(-1, UserRoleEnum.OWNER.getRole());
        Assert.isTrue(affectRow == 0, "modifyUserRole_invalidId_return0 fail");
    }

    @Test
    public void modifyUserRole_validId_return1() throws Exception {
        userDao.modifyUserRole(userId, UserRoleEnum.OWNER.getRole());
        User user = userDao.queryUserById(userId);
        Assert.isTrue(user.getRole() == UserRoleEnum.OWNER.getRole(), "modifyUserRole_validRole_return1 fail");
    }

    @Test
    public void modifyUserStatus_invalidId_return0() throws Exception {
        int affectRow = userDao.modifyUserStatus(-1, UserStatusEnum.INACTIVE.getStatus());
        Assert.isTrue(affectRow == 0, "modifyUserStatus_invalidId_return0 fail");
    }

    @Test
    public void modifyUserStatus_validId_return1() throws Exception {
        int affectRow = userDao.modifyUserStatus(userId, UserStatusEnum.INACTIVE.getStatus());
        Assert.isTrue(affectRow == 1, "modifyUserStatus_validId_return1 fail");
        User user = userDao.queryUserById(userId);
        checkUserEquality(user, "customer", "password", UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.INACTIVE.getStatus());
    }

    @After
    public void cleanup() {
        userDao.deleteUserById(userId);
        userDao.deleteUserByName("test");
    }
}
