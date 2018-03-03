package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Restaurant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestaurantDao {
    int createRestaurant(final Restaurant restaurant);
    Restaurant queryRestaurantById(long restaurantId);
    Restaurant queryRestaurantByName(String name);
    Restaurant queryRestaurantByPhone(String phone);
    List<Restaurant> queryRestaurantByCity(String city);
    List<Restaurant> queryRestaurantByState(String state);
    List<Restaurant> queryRestaurantByZip(String state);
    Restaurant queryRestaurantByAddress(@Param("street") String street, @Param("city") String city, @Param("state") String state, @Param("zip") String zip);
    List<Restaurant> queryAll();
    int modifyRestaurantName(@Param("restaurantId") long restaurantId, @Param("name") String name);
    int modifyRestaurantPhone(@Param("restaurantId") long restaurantId, @Param("phone") String phone);
    int modifyRestaurantAddress(@Param("restaurantId") long restaurantId, @Param("street") String street, @Param("city") String city, @Param("state") String state, @Param("zip") String zip);
    int deleteRestaurantById(long restaurantId);
    int deleteRestaurantByName(String name);
}
