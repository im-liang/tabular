package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:config/spring/dao.xml", "classpath:config/spring/service.xml" })
@SpringBootTest
public class RestaurantDaoTest {
    @Autowired
    RestaurantDao restaurantDao;

    @Before
    public void createTestRestaurants() {
        restaurantDao.createRestaurant("test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790");
        restaurantDao.createRestaurant("test2", "4342967927", "2 quaker rd", "stony brook", "New York", "11790");
        restaurantDao.createRestaurant("test3", "4342967928", "3 quaker rd", "brooklyn", "New York", "11219");
    }

    @Test
    public void testCreateRestaurant() throws Exception {
        int result = restaurantDao.createRestaurant("test4", "4342967929", "4 quaker rd", "hoboken", "New Jersey", "15550");
        System.out.println(result);
    }

    @Test
    public void testQueryRestaurant() throws Exception {
        Restaurant restaurant = restaurantDao.queryRestaurantByName("test1");
    }

    @After
    public void deleteTestRestaurants() {
        restaurantDao.deleteRestaurantByName("test1");
        restaurantDao.deleteRestaurantByName("test2");
        restaurantDao.deleteRestaurantByName("test3");
        restaurantDao.deleteRestaurantByName("test4");
    }
}
