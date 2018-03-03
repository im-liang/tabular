package com.tabular.tabular.dao;

import com.tabular.tabular.BaseTests;
import com.tabular.tabular.entity.Restaurant;
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

public class RestaurantDaoTest extends BaseTests {
    @Autowired
    RestaurantDao restaurantDao;

    @Before
    public void createTestRestaurants() {
        restaurantDao.createRestaurant(new Restaurant("test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790"));
        restaurantDao.createRestaurant(new Restaurant("test2", "4342967927", "2 quaker rd", "stony brook", "New York", "11790"));
        restaurantDao.createRestaurant(new Restaurant("test3", "4342967928", "3 quaker rd", "brooklyn", "New York", "11219"));
    }

    private void checkRestaurantEquality(Restaurant restaurant, String name, String phone, String street, String city, String state, String zip) {
        System.out.println("restaurant id: " + restaurant.getRestaurantId());
        Assert.isTrue(restaurant.getName().equals(name), "restaurant name is not matched");
        Assert.isTrue(restaurant.getPhone().equals(phone), "restaurant phone is not matched");
        Assert.isTrue(restaurant.getStreet().equals(street), "restaurant street is not matched");
        Assert.isTrue(restaurant.getCity().equals(city), "restaurant city is not matched");
        Assert.isTrue(restaurant.getState().equals(state), "restaurant state is not matched");
        Assert.isTrue(restaurant.getZip().equals(zip), "restaurant zip code is not matched");
    }

    @Test
    public void testCreateRestaurant() throws Exception {
        int result = restaurantDao.createRestaurant(new Restaurant("test4", "4342967929", "4 quaker rd", "hoboken", "New Jersey", "15550"));
        System.out.println(result);
    }

    @Test
    public void testQueryRestaurantByName() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
        checkRestaurantEquality(restaurant, "test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790");
    }

    @Test
    public void testQueryRestaurantByPhone() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByPhone("4342967926");
        checkRestaurantEquality(restaurant, "test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790");
    }

    @Test
    public void testQueryRestaurantByCity() throws Exception {
        List<Restaurant> stonyBrookList = restaurantDao.queryRestaurantByCity("stony brook");
        Assert.isTrue(stonyBrookList.size() == 2, "testQueryRestaurantByCity failed");

        List<Restaurant> brooklynList = restaurantDao.queryRestaurantByCity("brooklyn");
        Assert.isTrue(brooklynList.size() == 1, "testQueryRestaurantByCity failed");
    }

    @Test
    public void testQueryRestaurantByState() throws Exception {
        List<Restaurant> list = restaurantDao.queryRestaurantByState("New York");
        Assert.isTrue(list.size() == 3, "testQueryRestaurantByState failed");
    }

    @Test
    public void testQueryRestaurantByZip() throws Exception {
        List<Restaurant> stonyBrookList = restaurantDao.queryRestaurantByZip("11790");
        Assert.isTrue(stonyBrookList.size() == 2, "testQueryRestaurantByZip failed");

        List<Restaurant> brooklynList = restaurantDao.queryRestaurantByZip("11219");
        Assert.isTrue(brooklynList.size() == 1, "testQueryRestaurantByZip failed");
    }

    @Test
    public void testQueryRestaurantByAddress() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByAddress("1 quaker rd", "stony brook", "New York", "11790");
        checkRestaurantEquality(restaurant, "test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790");
    }

    @Test
    public void testUpdateRestaurantName() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
        restaurantDao.modifyRestaurantName(restaurant.getRestaurantId(), "modified");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getName().equals("modified"), "testUpdateRestaurantName failed");
    }

    @Test
    public void testUpdateRestaurantPhone() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
        restaurantDao.modifyRestaurantPhone(restaurant.getRestaurantId(), "4346668888");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getPhone().equals("4346668888"), "testUpdateRestaurantPhone failed");
    }

    @Test
    public void testUpdateRestaurantAddress() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
        restaurantDao.modifyRestaurantAddress(restaurant.getRestaurantId(), "5 quaker rd", "hoboken", "New Jersey", "15550");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getStreet().equals("5 quaker rd"), "testUpdateRestaurantPhone failed");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getCity().equals("hoboken"), "testUpdateRestaurantPhone failed");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getState().equals("New Jersey"), "testUpdateRestaurantPhone failed");
        Assert.isTrue(restaurantDao.queryRestaurantById(restaurant.getRestaurantId()).getZip().equals("15550"), "testUpdateRestaurantPhone failed");
    }

    @Test
    public void testDeleteRestaurantById() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
        long id = restaurant.getRestaurantId();
        int result = restaurantDao.deleteRestaurantById(id);
        System.out.println("testDeleteRestaurantById: " + result);
        Assert.isTrue(restaurantDao.queryRestaurantById(id) == null, "testDeleteRestaurantById failed");
    }

    @Test
    public void testDeleteRestaurantByName() throws Exception {
        restaurantDao.deleteRestaurantByName("test1");
        int result = restaurantDao.deleteRestaurantByName("test1");
        System.out.println("testDeleteRestaurantById: " + result);
        Assert.isTrue(restaurantDao.queryRestaurantByName("test1") == null, "testDeleteRestaurantById failed");
    }

    @After
    public void deleteTestRestaurants() {
        restaurantDao.deleteRestaurantByName("test1");
        restaurantDao.deleteRestaurantByName("test2");
        restaurantDao.deleteRestaurantByName("test3");
        restaurantDao.deleteRestaurantByName("test4");
        restaurantDao.deleteRestaurantByName("modified");
    }
}
