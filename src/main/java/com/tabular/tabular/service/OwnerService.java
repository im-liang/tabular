package com.tabular.tabular.service;

import com.tabular.tabular.dao.OwnerDao;
import com.tabular.tabular.entity.Owner;
import com.tabular.tabular.entity.Waiter;
import com.tabular.tabular.exception.NoSuchOwnerException;
import com.tabular.tabular.exception.NoSuchWaiterException;
import com.tabular.tabular.service.blueprint.OwnerServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService implements OwnerServiceBlueprint {
    @Autowired
    private UserService userService;
    @Autowired
    private OwnerDao ownerDao;

    @Override
    public Owner queryOwnerById(long ownerId) {
        if(Validator.validateUserId(ownerId)) {
            return ownerDao.queryOwnerById(ownerId);
        }else {
            return null;
        }
    }

    @Override
    public List<Owner> queryOwnerByRestaurant(long restaurantId) {
        if(Validator.validateUserId(restaurantId)) {
            return ownerDao.queryOwnerByRestaurant(restaurantId);
        }else {
            return null;
        }
    }

    @Override
    public boolean modifyOwnerFullName(long ownerId, String firstName, String lastName) {
        if(Validator.validateUserId(ownerId) && Validator.validateName(firstName) && Validator.validateName(lastName)) {
            int result = ownerDao.modifyOwnerFullName(ownerId, firstName, lastName);
            return result == 1;
        }else {
            return false;
        }
    }

    @Override
    public long createOwner(String firstName, String lastName, long restaurantId) {
        if(Validator.validateName(firstName) && Validator.validateName(lastName) && Validator.validateUserId(restaurantId)) {
            String username = UUID.randomUUID().toString();
            String password = UUID.randomUUID().toString();
            long userId = userService.createUserAsWaiter(username, password);

            return ownerDao.insertOwner(userId, firstName, lastName, restaurantId);
        }else {
            return -1;
        }
    }
}
