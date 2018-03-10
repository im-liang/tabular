package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Restaurant;

import java.util.List;

public interface RestaurantServiceBlueprint {
    Restaurant queryRestaurantById(long restaurantId);
    Restaurant queryRestaurantByName(String name);
    Restaurant queryRestaurantByPhone(String phone);
    List<Restaurant> queryRestaurantByCity(String city);
    List<Restaurant> queryRestaurantByState(String state);
    List<Restaurant> queryRestaurantByZip(String zip);
    Restaurant queryRestaurantByAddress(String street, String city, String state, String zip);
    List<Restaurant> queryAll();
    long createRestaurant(String name, String phone, String street, String city, String state, String zip);
    boolean modifyRestaurantName(long restaurantId, String name);
    boolean modifyRestaurantPhone(long restaurantId, String phone);
    boolean modifyRestaurantAddress(long restaurantId, String street, String city, String state, String zip);
    boolean deleteRestaurantById(long restaurantId);
    boolean deleteRestaurantByName(String name);
}
