package com.tabular.tabular.service;

import com.tabular.tabular.dao.WaiterDao;
import com.tabular.tabular.entity.Waiter;
import com.tabular.tabular.exception.NoSuchCustomerException;
import com.tabular.tabular.exception.NoSuchWaiterException;
import com.tabular.tabular.service.blueprint.WaiterServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WaiterService implements WaiterServiceBlueprint {
    @Autowired
    private UserService userService;
    @Autowired
    private WaiterDao waiterDao;

    @Override
    public Waiter queryWaiterById(long waiterId) {
        if(Validator.validateUserId(waiterId)) {
            return waiterDao.queryWaiterById(waiterId);
        }else {
            return null;
        }
    }

    @Override
    public List<Waiter> queryWaiterUnderOwnerByName(long ownerId, String name) {
        if(Validator.validateUserId(ownerId) && Validator.validateName(name)) {
            return waiterDao.queryWaiterUnderOwnerByName(ownerId, name);
        }else {
            return null;
        }
    }

    @Override
    public List<Waiter> queryWaiterByOwner(long ownerId) {
        if(Validator.validateUserId(ownerId)) {
            return waiterDao.queryWaiterByOwner(ownerId);
        }else {
            return null;
        }
    }

    @Override
    public boolean modifyWaiterName(long waiterId, String name) {
        if(Validator.validateUserId(waiterId) && Validator.validateName(name)) {
            int result = waiterDao.modifyWaiterName(waiterId, name);
            if(result == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long createWaiter(long ownerId, String name) {
        if(Validator.validateUserId(ownerId) && Validator.validateName(name)) {
            String username = UUID.randomUUID().toString();
            String password = UUID.randomUUID().toString();
            long userId = userService.createUserAsWaiter(username, password);

            return waiterDao.insertWaiter(userId, name, ownerId);
        } else {
            return -1;
        }
    }
}
