package com.tabular.tabular.service;

import com.tabular.tabular.dao.WaiterDao;
import com.tabular.tabular.entity.Waiter;
import com.tabular.tabular.exception.owner.NoSuchOwnerException;
import com.tabular.tabular.exception.waiter.NoSuchWaiterException;
import com.tabular.tabular.service.blueprint.WaiterServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WaiterService implements WaiterServiceBlueprint {

    private final WaiterDao waiterDao;
    private final UserService userService;
    private final OwnerService ownerService;

    @Autowired
    public WaiterService(UserService userService, OwnerService ownerService, WaiterDao waiterDao) {
        this.userService = userService;
        this.ownerService = ownerService;
        this.waiterDao = waiterDao;
    }

    @Override
    public Waiter queryWaiterById(long waiterId) {
        Waiter waiter = waiterDao.queryWaiterById(waiterId);
        if(waiter == null) {
            throw new NoSuchWaiterException("waiter with id: " + waiterId + " does not exist");
        }
        return waiter;
    }

    @Override
    public List<Waiter> queryWaiterUnderOwnerByName(long ownerId, String name) {
        if(!ownerService.isOwnerExist(ownerId)) {
            throw new NoSuchOwnerException("owner with id: " + ownerId + " does not exist");
        }
        return waiterDao.queryWaiterUnderOwnerByName(ownerId, name);
    }

    @Override
    public List<Waiter> queryWaiterByOwner(long ownerId) {
        if(!ownerService.isOwnerExist(ownerId)) {
            throw new NoSuchOwnerException("owner with id: " + ownerId + " does not exist");
        }

        return waiterDao.queryWaiterByOwner(ownerId);
    }

    @Override
    public boolean modifyWaiterName(long waiterId, String name) {
        if(!isWaiterExist(waiterId)) {
            throw new NoSuchWaiterException("waiter with id: " + waiterId + " does not exist");
        }
        int result = waiterDao.modifyWaiterName(waiterId, name);
        return result == 1;
    }

    @Override
    public long createWaiter(long ownerId, String name) {
        if(!ownerService.isOwnerExist(ownerId)) {
            throw new NoSuchOwnerException("owner with id: " + ownerId + " does not exist");
        }


        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        long userId = userService.createUserAsWaiter(username, password);

        return waiterDao.insertWaiter(userId, name, ownerId);
    }

    public boolean isWaiterExist(long waiterId) {
        return queryWaiterById(waiterId) != null;
    }
}
