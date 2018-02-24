package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Owner;
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
public class OwnerDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    OwnerDao ownerDao;
    @Autowired
    RestaurantDao restaurantDao;

    private long userId1;
    private long userId2;
    private long restaurantId1;
    private long restaurantId2;

    private void checkOwnerEquality(Owner owner, long ownerId, String firstName, String lastName, long restaurantId) {
        System.out.println("owner id: " + owner.getOwnerId());
        Assert.isTrue(owner.getOwnerId() == ownerId, "owner id not match");
        Assert.isTrue(owner.getFirstName().equals(firstName), "owner first name not match");
        Assert.isTrue(owner.getLastName().equals(lastName), "owner last name not match");
        Assert.isTrue(owner.getRestaurantId() == restaurantId, "restaurant id not match");
    }

    @Before
    public void initTest() {
        userDao.createUser("user1", "password", UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser("user2", "password", UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userId1 = userDao.queryUserByUsername("user1").getUserId();
        userId2 = userDao.queryUserByUsername("user2").getUserId();

        restaurantDao.createRestaurant("res", "s", "q", "s", "s", "sdf");
        restaurantDao.createRestaurant("yo", "df", "a", "a", "a", "bb");
        restaurantId1 = restaurantDao.queryRestaurantByName("res").getRestaurantId();
        restaurantId2 = restaurantDao.queryRestaurantByName("yo").getRestaurantId();
        ownerDao.insertOwner(userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testCreateOwner() throws Exception {
        int result = ownerDao.insertOwner(userId1, "test", "pass", restaurantId1);
        System.out.println(result);
    }

    @Test
    public void testQueryOwnerById() throws Exception {
        Owner owner = ownerDao.queryOwnerById(userId2);
        checkOwnerEquality(owner, userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testQueryOwnerByFirstName() throws Exception {
        List<Owner> owner = ownerDao.queryOwnerByFirstName("owner2");
        Assert.isTrue(owner.size() == 1, "testQueryOwnerByFirstName fail");
        checkOwnerEquality(owner.get(0), userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testQueryOwnerByLastName() throws Exception {
        List<Owner> owner = ownerDao.queryOwnerByLastName("last2");
        Assert.isTrue(owner.size() == 1, "testQueryOwnerByLastName fail");
        checkOwnerEquality(owner.get(0), userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testQueryOwnerByFullName() throws Exception {
        List<Owner> owner = ownerDao.queryOwnerByFullName("owner2", "last2");
        Assert.isTrue(owner.size() == 1, "testQueryOwnerByFullName fail");
        checkOwnerEquality(owner.get(0), userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testQueryOwnerByRestaurant() throws Exception {
        List<Owner> owner = ownerDao.queryOwnerByRestaurant(restaurantId2);
        Assert.isTrue(owner.size() == 1, "testQueryOwnerByRestaurant fail");
        checkOwnerEquality(owner.get(0), userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Owner> owner = ownerDao.queryAll();
        Assert.isTrue(owner.size() == 1, "testQueryAll fail");
        checkOwnerEquality(owner.get(0), userId2, "owner2", "last2", restaurantId2);
    }

    @Test
    public void testModifyOwnerFirstName() throws Exception {
        ownerDao.modifyOwnerFirstName(userId2, "yo");
        Owner owner = ownerDao.queryOwnerById(userId2);
        checkOwnerEquality(owner, userId2, "yo", "last2", restaurantId2);
    }

    @Test
    public void testModifyOwnerLastName() throws Exception {
        ownerDao.modifyOwnerLastName(userId2, "yo");
        Owner owner = ownerDao.queryOwnerById(userId2);
        checkOwnerEquality(owner, userId2, "owner2", "yo", restaurantId2);
    }

    @Test
    public void testModifyOwnerFullName() throws Exception {
        ownerDao.modifyOwnerFullName(userId2, "yo", "friday");
        Owner owner = ownerDao.queryOwnerById(userId2);
        checkOwnerEquality(owner, userId2, "yo", "friday", restaurantId2);
    }

    @After
    public void cleanup() {
        userDao.deleteUserById(userId1);
        userDao.deleteUserById(userId2);
        restaurantDao.deleteRestaurantById(restaurantId1);
        restaurantDao.deleteRestaurantById(restaurantId2);
    }
}
