package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Restaurant;
import com.tabular.tabular.entity.Table;
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
public class TableDaoTest {
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    TableDao tableDao;

    private long restaurantId1;
    private long restaurantId2;

    @Before
    public void createTestRestaurantTables() {
        restaurantDao.createRestaurant("test1", "4342967926", "1 quaker rd", "stony brook", "New York", "11790");
        restaurantDao.createRestaurant("test2", "4342967927", "2 quaker rd", "stony brook", "New York", "11790");
        Restaurant restaurant1 = restaurantDao.queryRestaurantByName("test1");
        Restaurant restaurant2 = restaurantDao.queryRestaurantByName("test2");
        restaurantId1 = restaurant1.getRestaurantId();
        restaurantId2 = restaurant2.getRestaurantId();
        tableDao.insertTable(new Table("table1", 3, restaurantId1));
        tableDao.insertTable(new Table("table2", 2, restaurantId1));
        tableDao.insertTable(new Table("table1", 2, restaurantId2));
    }

    @Test
    public void testCreateTable() throws Exception {
        int result = tableDao.insertTable(new Table("table3", 3, restaurantId1));
        System.out.println(result);
    }

    @Test
    public void testQueryRestaurantTableByName() throws Exception {
        Table table = tableDao.queryRestaurantTableByName("table1", restaurantId1);
        Assert.isTrue(table != null, "testQueryRestaurantTableByName fail");
    }

    @Test
    public void testQueryRestaurantTableByLimit() throws Exception {
        List<Table> table = tableDao.queryRestaurantTableByLimit(restaurantId1, 2);
        Assert.isTrue(table.size() == 1, "testQueryRestaurantTableByLimit fail");
    }

    @Test
    public void testQueryRestaurantTableByLessLimit() throws Exception {
        List<Table> table1 = tableDao.queryRestaurantTableByLessLimit(restaurantId1, 3);
        Assert.isTrue(table1.size() == 2, "testQueryRestaurantTableByLessLimit with restaurantId1 fail");
        List<Table> table2 = tableDao.queryRestaurantTableByLessLimit(restaurantId2, 1);
        Assert.isTrue(table2.size() == 0, "testQueryRestaurantTableByLessLimit with restaurantId2 fail");
    }

    @Test
    public void testQueryRestaurantTableByMoreLimit() throws Exception {
        List<Table> table1 = tableDao.queryRestaurantTableByMoreLimit(restaurantId1, 2);
        Assert.isTrue(table1.size() == 2, "testQueryRestaurantTableByMoreLimit fail");
        List<Table> table2 = tableDao.queryRestaurantTableByMoreLimit(restaurantId1, 5);
        Assert.isTrue(table2.size() == 0, "testQueryRestaurantTableByMoreLimit fail");
    }

    @Test
    public void testQueryRestaurantTableByLimitRange() throws Exception {
        List<Table> table1 = tableDao.queryRestaurantTableByLimitRange(restaurantId1, 2, 3);
        Assert.isTrue(table1.size() == 2, "testQueryRestaurantTableByLimitRange fail");
        List<Table> table2 = tableDao.queryRestaurantTableByLimitRange(restaurantId1, 5, 10);
        Assert.isTrue(table2.size() == 0, "testQueryRestaurantTableByLimitRange fail");
    }

    @Test
    public void testQueryRestaurantTableByRestaurant() throws Exception {
        List<Table> table1 = tableDao.queryTableByRestaurant(restaurantId1);
        Assert.isTrue(table1.size() == 2, "testQueryRestaurantTableByRestaurant fail");
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Table> table1 = tableDao.queryAll();
        Assert.isTrue(table1.size() == 3, "testQueryAll fail");
    }

    @After
    public void deleteTestRestaurants() {
        restaurantDao.deleteRestaurantByName("test1");
        restaurantDao.deleteRestaurantByName("test2");
    }
}
