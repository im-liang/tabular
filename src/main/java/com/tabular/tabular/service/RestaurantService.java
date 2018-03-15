package com.tabular.tabular.service;

import com.tabular.tabular.dao.RestaurantDao;
import com.tabular.tabular.entity.Restaurant;
import com.tabular.tabular.exception.relation.RelationAlreadyExistException;
import com.tabular.tabular.exception.restaurant.NoSuchRestaurantException;
import com.tabular.tabular.service.blueprint.RestaurantServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements RestaurantServiceBlueprint {

    private final RestaurantDao restaurantDao;

    @Autowired
    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    public Restaurant queryRestaurantById(long restaurantId) {
        Restaurant restaurant = restaurantDao.queryRestaurantById(restaurantId);
        if(restaurant == null) {
            throw new NoSuchRestaurantException("restaurant with id: " + restaurantId + "does not exist");
        }
        return restaurant;
    }

    @Override
    public Restaurant queryRestaurantByName(String name) {
        return restaurantDao.queryRestaurantByName(name);
    }

    @Override
    public Restaurant queryRestaurantByPhone(String phone) {
        return restaurantDao.queryRestaurantByPhone(phone);
    }

    @Override
    public List<Restaurant> queryRestaurantByCity(String city) {
        return restaurantDao.queryRestaurantByCity(city);
    }

    @Override
    public List<Restaurant> queryRestaurantByState(String state) {
        return restaurantDao.queryRestaurantByState(state);
    }

    @Override
    public List<Restaurant> queryRestaurantByZip(String zip) {
        return restaurantDao.queryRestaurantByZip(zip);
    }

    @Override
    public Restaurant queryRestaurantByAddress(String street, String city, String state, String zip) {
        return restaurantDao.queryRestaurantByAddress(street, city, state, zip);
    }

    @Override
    public List<Restaurant> queryAll() {
        return restaurantDao.queryAll();
    }

    @Override
    public long createRestaurant(Restaurant restaurant) {
        if(isRestaurantNameExist(restaurant.getName())) {
            throw new RelationAlreadyExistException("restaurant with name : " + restaurant.getName() + "already exists");
        }
        return restaurantDao.createRestaurant(restaurant);
    }

    @Override
    public boolean modifyRestaurantName(long restaurantId, String name) {
        if(!isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }
        int result = restaurantDao.modifyRestaurantName(restaurantId, name);
        return result == 1;
    }

    @Override
    public boolean modifyRestaurantPhone(long restaurantId, String phone) {
        if(!isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }
        int result = restaurantDao.modifyRestaurantPhone(restaurantId, phone);
        return result == 1;
    }

    @Override
    public boolean modifyRestaurantAddress(long restaurantId, String street, String city, String state, String zip) {
        if(!isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }
        int result = restaurantDao.modifyRestaurantAddress(restaurantId, street, city, state, zip);
        return result == 1;
    }

    @Override
    public boolean deleteRestaurantById(long restaurantId) {
        if(!isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }
        int result = restaurantDao.deleteRestaurantById(restaurantId);
        return result == 1;
    }

    @Override
    public boolean deleteRestaurantByName(String name) {
        int result = restaurantDao.deleteRestaurantByName(name);
        return result == 1;
    }

    public boolean isRestaurantExist(long restaurantId) {
        return restaurantDao.queryRestaurantById(restaurantId) != null;
    }

    private boolean isRestaurantNameExist(String name) {
        return restaurantDao.queryRestaurantByName(name) != null;
    }
}
