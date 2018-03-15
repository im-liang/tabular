package com.tabular.tabular.service;

import com.tabular.tabular.dao.UserDao;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import com.tabular.tabular.exception.user.NoSuchUserException;
import com.tabular.tabular.exception.user.UserAlreadyExistException;
import com.tabular.tabular.service.blueprint.UserServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceBlueprint {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User queryUserById(long userId) {
        User user = userDao.queryUserById(userId);
        if(user == null) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }
        return user;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if(user == null) {
            throw new NoSuchUserException("user with username: " + username + " does not exist");
        }
        return user;
    }

    @Override
    public boolean changeUsername(long userId, String username) {
        if(!isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        int affectedRow = userDao.modifyUsername(userId, username);
        return affectedRow == 1;
    }

    @Override
    public boolean changePassword(long userId, String password) {
        if(!isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        int affectedRow = userDao.modifyUserPassword(userId, password);
        return affectedRow == 1;
    }

    @Override
    public boolean deleteUserById(long userId) {
        if(!isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        int affectedRow = userDao.deleteUserById(userId);
        return affectedRow == 1;
    }

    @Override
    public boolean deleteUserByName(String username) {
        if(!isUserExist(username)) {
            throw new NoSuchUserException("user with username: " + username + " does not exist");
        }

        int affectedRow = userDao.deleteUserByName(username);
        return affectedRow == 1;
    }

    @Override
    public long createUserAsCustomer(String username, String password) {
        if(isUserExist(username)) {
            throw new UserAlreadyExistException("user with username: " + username + " already exists");
        }

        User user = new User(username, password, UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    @Override
    public long createUserAsWaiter(String username, String password) {
        if(isUserExist(username)) {
            throw new UserAlreadyExistException("user with username: " + username + " already exists");
        }

        User user = new User(username, password, UserRoleEnum.WAITER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    @Override
    public long createUserAsOwner(String username, String password) {
        if(isUserExist(username)) {
            throw new UserAlreadyExistException("user with username: " + username + " already exists");
        }

        User user = new User(username, password, UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    @Override
    public boolean activateUser(long userId) {
        if(!isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.ACTIVE.getStatus());
        return affectedRow == 1;
    }

    @Override
    public boolean deactivateUser(long userId) {
        if(!isUserExist(userId)) {
            throw new NoSuchUserException("user with id: " + userId + " does not exist");
        }

        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.INACTIVE.getStatus());
        return affectedRow == 1;
    }

    public boolean isUserExist(long userId) {
        return userDao.queryUserById(userId) != null;
    }

    public boolean isUserExist(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
