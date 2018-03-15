package com.tabular.tabular.service;

import com.tabular.tabular.dao.OwnerDao;
import com.tabular.tabular.entity.Owner;
import com.tabular.tabular.exception.owner.NoSuchOwnerException;
import com.tabular.tabular.exception.restaurant.NoSuchRestaurantException;
import com.tabular.tabular.service.blueprint.OwnerServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService implements OwnerServiceBlueprint {

    private final OwnerDao ownerDao;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Autowired
    public OwnerService(UserService userService, RestaurantService restaurantService, OwnerDao ownerDao) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.ownerDao = ownerDao;
    }

    @Override
    public Owner queryOwnerById(long ownerId) {
        Owner owner = ownerDao.queryOwnerById(ownerId);
        if(owner == null) {
            throw new NoSuchOwnerException("owner with " + ownerId + " does not exist");
        }
        return owner;
    }

    @Override
    public List<Owner> queryOwnerByRestaurant(long restaurantId) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }
        return ownerDao.queryOwnerByRestaurant(restaurantId);
    }

    @Override
    public boolean modifyOwnerFullName(long ownerId, String firstName, String lastName) {
        if(!isOwnerExist(ownerId)) {
            throw new NoSuchOwnerException("owner with " + ownerId + "does not exist");
        }
        int result = ownerDao.modifyOwnerFullName(ownerId, firstName, lastName);
        return result == 1;
    }

    @Override
    public long createOwner(String firstName, String lastName, long restaurantId) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + "does not exist");
        }

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        long userId = userService.createUserAsWaiter(username, password);

        return ownerDao.insertOwner(userId, firstName, lastName, restaurantId);
    }

    public boolean isOwnerExist(long ownerId) {
        return ownerDao.queryOwnerById(ownerId) != null;
    }
}
