package com.tabular.tabular.service;

import com.tabular.tabular.dao.RestaurantDao;
import com.tabular.tabular.entity.Restaurant;
import com.tabular.tabular.service.blueprint.RestaurantServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements RestaurantServiceBlueprint {
    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public Restaurant queryRestaurantById(long restaurantId) {
        if(Validator.validateRestaurantId(restaurantId)) {
            return restaurantDao.queryRestaurantById(restaurantId);
        }else {
            return null;
        }
    }

    @Override
    public Restaurant queryRestaurantByName(String name) {
        if(Validator.validateName(name)) {
            return restaurantDao.queryRestaurantByName(name);
        }else {
            return null;
        }
    }

    @Override
    public Restaurant queryRestaurantByPhone(String phone) {
        if(Validator.validatePhone(phone)) {
            return restaurantDao.queryRestaurantByPhone(phone);
        } else {
            return null;
        }
    }

    @Override
    public List<Restaurant> queryRestaurantByCity(String city) {
        if(Validator.validateCity(city)) {
            return restaurantDao.queryRestaurantByCity(city);
        }else {
            return null;
        }
    }

    @Override
    public List<Restaurant> queryRestaurantByState(String state) {
        if(Validator.validateState(state)) {
            return restaurantDao.queryRestaurantByState(state);
        }else {
            return null;
        }
    }

    @Override
    public List<Restaurant> queryRestaurantByZip(String zip) {
        if(Validator.validateZip(zip)) {
            return restaurantDao.queryRestaurantByZip(zip);
        }else {
            return null;
        }
    }

    @Override
    public Restaurant queryRestaurantByAddress(String street, String city, String state, String zip) {
        if(Validator.validateStreet(street) && Validator.validateCity(city) && Validator.validateState(state) && Validator.validateZip(zip)) {
            return restaurantDao.queryRestaurantByAddress(street, city, state, zip);
        }else {
            return null;
        }
    }

    @Override
    public List<Restaurant> queryAll() {
        return restaurantDao.queryAll();
    }

    @Override
    public long createRestaurant(String name, String phone, String street, String city, String state, String zip) {
        if(Validator.validateName(name) && Validator.validatePhone(phone) && Validator.validateStreet(street) && Validator.validateCity(city) && Validator.validateState(state) && Validator.validateZip(zip)) {
            Restaurant restaurant = new Restaurant(name, phone, street, city, state, zip);
            return restaurantDao.createRestaurant(restaurant);
        }else {
            return -1;
        }
    }

    @Override
    public boolean modifyRestaurantName(long restaurantId, String name) {
        if(Validator.validateUserId(restaurantId) && Validator.validateName(name)) {
            int result = restaurantDao.modifyRestaurantName(restaurantId, name);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public boolean modifyRestaurantPhone(long restaurantId, String phone) {
        if(Validator.validateUserId(restaurantId) && Validator.validatePhone(phone)) {
            int result = restaurantDao.modifyRestaurantPhone(restaurantId, phone);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public boolean modifyRestaurantAddress(long restaurantId, String street, String city, String state, String zip) {
        if(Validator.validateUserId(restaurantId) && Validator.validateStreet(street) && Validator.validateCity(city) && Validator.validateState(state) && Validator.validateZip(zip)) {
            int result = restaurantDao.modifyRestaurantAddress(restaurantId, street, city, state, zip);
            return result == 1;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteRestaurantById(long restaurantId) {
        if(Validator.validateUserId(restaurantId)) {
            int result = restaurantDao.deleteRestaurantById(restaurantId);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteRestaurantByName(String name) {
        if(Validator.validateName(name)) {
            int result = restaurantDao.deleteRestaurantByName(name);
            return result == 1;
        }else {
            return false;
        }
    }
}
