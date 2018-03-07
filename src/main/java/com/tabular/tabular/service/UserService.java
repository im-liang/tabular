package com.tabular.tabular.service;

import com.tabular.tabular.dao.UserDao;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import com.tabular.tabular.service.blueprint.UserServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceBlueprint {
    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(long userId) {
        if(Validator.validateUserId(userId)) {
            return userDao.queryUserById(userId);
        }else {
            return null;
        }
    }

    @Override
    public User queryUserByUsername(String username) {
        if(Validator.validateUsername(username)) {
            return userDao.queryUserByUsername(username);
        }else {
            return null;
        }
    }

    @Override
    public boolean changeUsername(long userId, String username) {
        if(Validator.validateUserId(userId) && Validator.validateUsername(username)) {
            return false;
        }

        if(queryUserByUsername(username) != null) {
            return false;
        }
        int affectedRow = userDao.modifyUsername(userId, username);
        return affectedRow == 1;
    }

    @Override
    public boolean changePassword(long userId, String password) {
        if(Validator.validateUserId(userId) && Validator.validatePassword(password)) {
            return false;
        }
        int affectedRow = userDao.modifyUserPassword(userId, password);
        return affectedRow == 1;
    }

    @Override
    public boolean deleteUserById(long userId) {
        if(Validator.validateUserId(userId)) {
            return false;
        }
        int affectedRow = userDao.deleteUserById(userId);
        return affectedRow == 1;
    }

    @Override
    public boolean deleteUserByName(String username) {
        if(Validator.validateUsername(username)) {
            return false;
        }
        int affectedRow = userDao.deleteUserByName(username);
        return affectedRow == 1;
    }

    @Override
    public long createUserAsCustomer(String username, String password) {
        if(Validator.validateUsername(username) && Validator.validatePassword(password) && queryUserByUsername(username) == null) {
            User user = new User(username, password, UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
            userDao.createUser(user);
            return user.getUserId();
        }else {
            return -1;
        }
    }

    @Override
    public long createUserAsWaiter(String username, String password) {
        if(Validator.validateUsername(username) && Validator.validatePassword(password) && queryUserByUsername(username) == null) {
            User user = new User(username, password, UserRoleEnum.WAITER.getRole(), UserStatusEnum.ACTIVE.getStatus());
            userDao.createUser(user);
            return user.getUserId();
        }else {
            return -1;
        }
    }

    @Override
    public long createUserAsOwner(String username, String password) {
        if(Validator.validateUsername(username) && Validator.validatePassword(password) && queryUserByUsername(username) == null) {
            User user = new User(username, password, UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
            userDao.createUser(user);
            return user.getUserId();
        }else {
            return -1;
        }
    }

    @Override
    public boolean activateUser(long userId) {
        if(Validator.validateUserId(userId)) {
            int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.ACTIVE.getStatus());
            return affectedRow == 1;
        }else {
            return false;
        }
    }

    @Override
    public boolean deactivateUser(long userId) {
        if(Validator.validateUserId(userId)) {
            int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.INACTIVE.getStatus());
            return affectedRow == 1;
        }else {
            return false;
        }
    }
}
