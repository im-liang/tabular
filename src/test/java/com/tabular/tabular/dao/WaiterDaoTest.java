package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.entity.Waiter;
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
public class WaiterDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    OwnerDao ownerDao;
    @Autowired
    WaiterDao waiterDao;
    @Autowired
    RestaurantDao restaurantDao;

    private long ownerId;
    private long waiterId;

    private void checkWaiterEquality(Waiter waiter, long waiterId, String name, long ownerId) {
        Assert.isTrue(waiter.getWaiterId() == waiterId, "waiter id not match");
        Assert.isTrue(waiter.getName().equals(name), "waiter name not match");
        Assert.isTrue(waiter.getOwnerId() == ownerId, "waiter's owner id not match");
    }

    @Before
    public void initTest() {
        userDao.createUser("owner", "password", UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser("waiter", "password", UserRoleEnum.WAITER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        restaurantDao.createRestaurant("qq", "qq", "qq", "qq", "qq", "qq");
        long restaurantId = restaurantDao.queryRestaurantByName("qq").getRestaurantId();
        ownerId = userDao.queryUserByUsername("owner").getUserId();
        waiterId = userDao.queryUserByUsername("waiter").getUserId();
        ownerDao.insertOwner(ownerId, "owner", "last1", restaurantId);
        waiterDao.insertWaiter(waiterId, "waiter", ownerId);
    }

    @Test
    public void testQueryWaiterById() throws Exception {
        Waiter waiter = waiterDao.queryWaiterById(waiterId);
        checkWaiterEquality(waiter, waiterId, "waiter", ownerId);
    }

    @Test
    public void testQueryWaiterByName() throws Exception {
        List<Waiter> waiter = waiterDao.queryWaiterByName("waiter");
        Assert.isTrue(waiter.size() == 1, "testQueryWaiterByName fail");
        checkWaiterEquality(waiter.get(0), waiterId, "waiter", ownerId);
    }

    @Test
    public void testQueryWaiterUnderOwnerByName() throws Exception {
        List<Waiter> waiter = waiterDao.queryWaiterUnderOwnerByName(ownerId, "waiter");
        Assert.isTrue(waiter.size() == 1, "testQueryWaiterUnderOwnerByName fail");
        checkWaiterEquality(waiter.get(0), waiterId, "waiter", ownerId);
    }

    @Test
    public void testQueryWaiterByOwner() throws Exception {
        List<Waiter> waiter = waiterDao.queryWaiterByOwner(ownerId);
        Assert.isTrue(waiter.size() == 1, "testQueryWaiterByOwner fail");
        checkWaiterEquality(waiter.get(0), waiterId, "waiter", ownerId);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Waiter> waiter = waiterDao.queryAll();
        Assert.isTrue(waiter.size() == 1, "testQueryAll fail");
        checkWaiterEquality(waiter.get(0), waiterId, "waiter", ownerId);
    }

    @Test
    public void testModifyWaiterName() throws Exception {
        waiterDao.modifyWaiterName(waiterId, "yo");
        Waiter waiter = waiterDao.queryWaiterById(waiterId);
        checkWaiterEquality(waiter, waiterId, "yo", ownerId);
    }

    @After
    public void cleanup() {
        userDao.deleteUserById(waiterId);
        userDao.deleteUserById(ownerId);
        restaurantDao.deleteRestaurantByName("qq");
    }
}
